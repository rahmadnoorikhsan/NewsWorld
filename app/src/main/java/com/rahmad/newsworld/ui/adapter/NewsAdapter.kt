package com.rahmad.newsworld.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahmad.newsworld.databinding.ItemNewsBinding
import com.rahmad.newsworld.domain.model.News
import com.rahmad.newsworld.utils.Extensions.showImageInto

class NewsAdapter(val data: (News) -> Unit) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.apply {
                ivNews.showImageInto(itemView.context, news.urlToImage)
                tvTitle.text = news.title
                tvAuthor.text = news.source?.name
            }
            itemView.setOnClickListener { data.invoke(news) }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem == newItem

            override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
        }
    }
}