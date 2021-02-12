package com.harsh.drc_practical.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.harsh.drc_practical.R
import com.harsh.drc_practical.base.Constants
import com.harsh.drc_practical.extension.getFormattedDate
import com.harsh.drc_practical.model.Article
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private var item: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        intent.extras?.let {
            item = intent.getSerializableExtra(Constants.NEWS_DATA) as Article
            item?.let { article ->
                title = article.author
                tvNewsTitle.text = article.title
                Glide.with(this)
                    .load(article.urlToImage)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .into(ivNewsTitle)
                tvNewsDate.text = article.publishedAt.getFormattedDate()
                tvNewsDetail.text = article.description
                ivNewsTitle.setOnClickListener {
                    startActivity(
                        Intent(this, ImageViewActivity::class.java)
                            .putExtra(Constants.IMAGE_URL, article.urlToImage)
                    )
                }
            }
        }
    }
}