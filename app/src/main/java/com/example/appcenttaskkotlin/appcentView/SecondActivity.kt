package com.example.appcenttaskkotlin.appcentView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.appcenttaskkotlin.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //TEst Class and activity

        val imgBack: ImageView = findViewById(R.id.img_back)
        val imgSend: ImageView = findViewById(R.id.img_send)
        val imgSave: ImageView = findViewById(R.id.img_favorite)
        val imageView: ImageView = findViewById(R.id.imageView)

        val tvContentTitle: TextView = findViewById(R.id.tv_content_title)
        val tvAuthor: TextView = findViewById(R.id.tv_author)
        val tvPublishedDate: TextView = findViewById(R.id.tv_published_date)
        val tvContent: TextView = findViewById(R.id.tv_content)

        val btnNewsSource: Button = findViewById(R.id.btn_news_source)


        val title:String = intent.getStringExtra("NewItemTitle").toString()
        val description:String = intent.getStringExtra("NewItemDescription").toString()
        val date:String = intent.getStringExtra("NewItemDate").toString()
        val imgURL: String = intent.getStringExtra("NewItemUrlToImage").toString()
        val author: String = intent.getStringExtra("NewItemAuthor").toString()
        val url: String = intent.getStringExtra("NewItemURL").toString()

        val bigText:String = "Title: $title \n Date: $date \n Author: $author \n URL: $url"

        tvContentTitle.text = title
        tvAuthor.text= author
        tvPublishedDate.text = date
        tvContent.text = description

        Glide.with(this)
            .load(imgURL)
            .into(imageView)

        imgBack.setOnClickListener(View.OnClickListener {super.onBackPressed()})
        imgSend.setOnClickListener(View.OnClickListener {SendData(bigText)})
        imgSave.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        })

        val intent = Intent(this, WebActivity::class.java)
        btnNewsSource.setOnClickListener(View.OnClickListener {
            intent.putExtra("URL",url)
            startActivity(intent)})

    }

    private fun SendData(text:String)
    {
        //For image use Intent.EXTRA_STREAM
        //For text use Intent.EXTRA_TEXT
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent,"Shared Data:\n"))
    }
}