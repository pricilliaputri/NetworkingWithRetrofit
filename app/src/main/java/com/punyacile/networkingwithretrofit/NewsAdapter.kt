package com.punyacile.networkingwithretrofit

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.punyacile.networkingwithretrofit.UpdateNewsActivity
import com.punyacile.networkingwithretrofit.databinding.ActivityMainBinding
import com.punyacile.networkingwithretrofit.databinding.ItemNewsBinding
import com.punyacile.networkingwithretrofit.model.ResponseDataNews
import com.punyacile.networkingwithretrofit.model.ResponseDataNewsItem

class NewsAdapter(private val listNews: List<ResponseDataNewsItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: ResponseDataNewsItem, context: Context) {
            binding.tvTitleNews.text = newsItem.title
            binding.tvDateNews.text = newsItem.createdAt
            Glide.with(context).load(newsItem.image).into(binding.imgViews)

            binding.btnSettings.setOnClickListener {
                val intent = Intent(context, UpdateNewsActivity::class.java)
                intent.putExtra("update", newsItem.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNews[position], holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}