package com.example.appcenttaskkotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcenttaskkotlin.R
import com.example.appcenttaskkotlin.appcentModel.Post2Articles

class AppcentAdapter(
    private val appcentList: ArrayList<Post2Articles>,
    private val listener: OnItemClickListener,
    private val mContext: Context
) : RecyclerView.Adapter<AppcentAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.appcent_items,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = appcentList[position]


//        holder.tvDesc.text = currentItem.description
        //Instead of description I changed it to show source to user because the
        //description was too much and was ugly in result

        holder.tvTitle.text = currentItem.title
        holder.tvDesc.text = currentItem.source.name
        holder.tvDate.text = currentItem.publishedAt

        val media = appcentList[position].urlToImage
        if (media != null) {
            Glide.with(mContext)
                .load(media)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.android)
        }
    }

    override fun getItemCount() = appcentList.size


    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.vholder_items_img)
        val tvTitle: TextView = itemView.findViewById(R.id.vholder_items_tv_title)
        val tvDesc: TextView = itemView.findViewById(R.id.vholder_items_tv_desc)
        val tvDate: TextView = itemView.findViewById(R.id.vholder_items_tv_published)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
//            val position: Int  = adapterPosition
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}