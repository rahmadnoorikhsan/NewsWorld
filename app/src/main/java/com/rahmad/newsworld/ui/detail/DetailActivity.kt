package com.rahmad.newsworld.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.navigation.navArgs
import com.rahmad.newsworld.R
import com.rahmad.newsworld.databinding.ActivityDetailBinding
import com.rahmad.newsworld.domain.model.News
import com.rahmad.newsworld.utils.DataMappers.toEntity
import com.rahmad.newsworld.utils.DateFormatter
import com.rahmad.newsworld.utils.Extensions.showImageInto
import dagger.hilt.android.AndroidEntryPoint

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val navArgs by navArgs<DetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        val news = navArgs.news
        populateDetail(news)
        setupToolbar(news.source?.name)
    }

    private fun setupToolbar(name: String?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = name ?: "Unknown"
        supportActionBar?.setHomeAsUpIndicator(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_arrow_back
            )
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun populateDetail(news: News) {
        binding.apply {
            ivNews.showImageInto(this@DetailActivity, news.urlToImage)
            tvTitle.text = news.title
            tvDescription.text = news.description
            tvAuthor.text = news.author
            tvDate.text = DateFormatter.formatDate(news.publishedAt ?: "")
            tvContent.text = HtmlCompat.fromHtml(
                news.content ?: "",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            btnSeeMore.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(news.url)
                startActivity(intent)
            }
        }
    }
}