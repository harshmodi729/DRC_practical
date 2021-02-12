package com.harsh.drc_practical.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.harsh.drc_practical.R
import com.harsh.drc_practical.base.Source
import com.harsh.drc_practical.data.APIResult
import com.harsh.drc_practical.extension.makeToast
import com.harsh.drc_practical.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(NewsViewModel::class.java)
        newsViewModel.getNewsHeadlines(Source.GOOGLE, getString(R.string.news_api_key))
        newsViewModel.newsLiveData.observe(this, {
            when (it) {
                is APIResult.Success -> {
                    makeToast("DONE")
                }
                is APIResult.Error -> {
                    makeToast(it.exception.message!!)
                }
            }
        })
    }
}