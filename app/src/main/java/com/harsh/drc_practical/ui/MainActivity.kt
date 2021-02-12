package com.harsh.drc_practical.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.harsh.drc_practical.R
import com.harsh.drc_practical.base.Source
import com.harsh.drc_practical.data.APIResult
import com.harsh.drc_practical.extension.isInternetAvailable
import com.harsh.drc_practical.extension.makeToast
import com.harsh.drc_practical.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(NewsViewModel::class.java)
        if (isInternetAvailable())
            newsViewModel.getNewsHeadlines(Source.GOOGLE, getString(R.string.news_api_key))
                .apply { progress.visibility = View.VISIBLE }
        else makeToast("Please check device internet.")
        newsViewModel.newsLiveData.observe(this, {
            progress.visibility = View.GONE
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