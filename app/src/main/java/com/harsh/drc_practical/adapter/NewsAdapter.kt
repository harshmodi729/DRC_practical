package com.harsh.drc_practical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.drc_practical.R
import com.harsh.drc_practical.model.Article

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var alNews = ArrayList<Article>()

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.lay_news_summary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = alNews.size

    fun addData(alNews: ArrayList<Article>) {
        this.alNews = alNews
        notifyDataSetChanged()
    }
}