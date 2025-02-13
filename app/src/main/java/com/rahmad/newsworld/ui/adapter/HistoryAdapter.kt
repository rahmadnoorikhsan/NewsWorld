package com.rahmad.newsworld.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.databinding.ItemHistoryBinding
import com.rahmad.newsworld.utils.DateFormatter
import com.rahmad.newsworld.utils.Extensions.showImageInto

class HistoryAdapter :
    ListAdapter<NewsEntity, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsEntity: NewsEntity) {
            binding.apply {
                ivNews.showImageInto(itemView.context, newsEntity.urlToImage)
                tvTitle.text = newsEntity.title
                tvDate.text = DateFormatter.formatDate(newsEntity.publishedAt ?: "")
            }
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(newsEntity.url)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity) =
                oldItem.title == newItem.title
        }
    }
}