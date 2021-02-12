package com.harsh.drc_practical.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harsh.drc_practical.R
import com.harsh.drc_practical.base.Constants
import com.harsh.drc_practical.model.Article

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var item: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        intent.extras?.let {
            item = intent.getSerializableExtra(Constants.NEWS_DATA) as Article
            title = item.author
        }
    }
}