package com.example.fail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsListAdapter(private val items: List<Article>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //val currentItem= items[position]
       // holder.titleView.text= currentItem.toString()
        holder.titleView.text=items[position].title

        holder.name.text=items[position].author

        Glide.with(holder.itemView)
            .load(items[position].urlToImage)
            .into(holder.imageView)

    }
}

class NewsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
    val titleView : TextView= itemView.findViewById(R.id.Title)
    val imageView: ImageView=itemView.findViewById(R.id.imageView)
    val name : TextView= itemView.findViewById(R.id.name)
}

