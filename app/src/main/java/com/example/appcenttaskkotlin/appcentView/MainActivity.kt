package com.example.appcenttaskkotlin.appcentView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttaskkotlin.R
import com.example.appcenttaskkotlin.adapters.AppcentAdapter
import com.example.appcenttaskkotlin.appcentModel.Post2Articles
import com.example.appcenttaskkotlin.appcentViewModel.MainViewModel2
import com.example.appcenttaskkotlin.appcentViewModel.MainViewModelFactory2
import com.example.appcenttaskkotlin.repository.Repository2

class MainActivity :
    AppCompatActivity(),
    AppcentAdapter.OnItemClickListener {
    //    private val exampleList = generateSampleList(5)
    private lateinit var articlesFullList: ArrayList<Post2Articles>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel2: MainViewModel2
    private lateinit var adapter: AppcentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articlesFullList = ArrayList<Post2Articles>()
        recyclerView = findViewById(R.id.rcl_main_1)
        getData()

    }

    override fun onItemClick(position: Int) {
        val clickedItem: Post2Articles = articlesFullList[position]

        adapter.notifyItemChanged(position)

        //in here we can go to in another page too
        val intent = Intent(this, SecondActivity::class.java)
// To pass any data to next activity
        intent.putExtra("NewItemTitle", clickedItem.title)
        intent.putExtra("NewItemDescription", clickedItem.description)
        intent.putExtra("NewItemDate", clickedItem.publishedAt)
        intent.putExtra("NewItemContent", clickedItem.content)
        intent.putExtra("NewItemUrlToImage", clickedItem.urlToImage)
        intent.putExtra("NewItemAuthor", clickedItem.author)
        intent.putExtra("NewItemURL", clickedItem.url)
// start your next activity
        startActivity(intent)
    }

    private fun getData() {
        val repository2 = Repository2()
        val viewModelFactory2 = MainViewModelFactory2(repository2)
        viewModel2 = ViewModelProvider(this, viewModelFactory2)
            .get(MainViewModel2::class.java)

        viewModel2.getPost1()

        viewModel2.myResponse1.observe(this, Observer { response1 ->
            if (response1.isSuccessful) {
                Log.d("onCreate: Response", response1.body()?.status.toString())
                Log.d("onCreate: Response", response1.body()?.totalResults.toString())
                articlesFullList = response1.body()?.articles!!
                SetRecycler()
                Log.d("onCreate: Response", "Author: ${articlesFullList[0].author}")
                Log.d("onCreate: Response", "Description: ${articlesFullList[0].description}")
                Log.d("onCreate: Response", "PublishedAt: ${articlesFullList[0].publishedAt}")
                Log.d("onCreate: Response", "Url: ${articlesFullList[0].url}")
                Log.d("onCreate: Response", "UrlToImage: ${articlesFullList[0].urlToImage}")
                Log.d("onCreate: Response", "Content: ${articlesFullList[0].content}")
                Log.d("onCreate: Response", "Source.Id: ${articlesFullList[0].source.id}")
                Log.d("onCreate: Response", "Source.Name: ${articlesFullList[0].source.name}")
            } else {
                Log.d("onCreate: Response", response1.errorBody().toString())
            }
        })

    }

    private fun SetRecycler() {
        adapter = AppcentAdapter(articlesFullList, this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}

