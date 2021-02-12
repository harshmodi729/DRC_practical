package com.harsh.drc_practical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harsh.drc_practical.R
import com.harsh.drc_practical.extension.getFormattedDate
import com.harsh.drc_practical.model.Article
import kotlinx.android.synthetic.main.lay_news_summary.view.*

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var alNews = ArrayList<Article>()
    var onNewsUrlClick: ((newsUrl: String) -> Unit)? = null
    var onNewsClick: ((item: Article) -> Unit)? = null

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvNews = itemView.cvNews as CardView
        val tvNewsTitle = itemView.tvNewsTitle as AppCompatTextView
        val ivNewsTitle = itemView.ivNewsTitle as AppCompatImageView
        val tvNewsAuthor = itemView.tvNewsAuthor as AppCompatTextView
        val tvNewsDate = itemView.tvNewsDate as AppCompatTextView
        val tvNewsUrl = itemView.tvNewsUrl as AppCompatTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.lay_news_summary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alNews[position]

        holder.tvNewsTitle.text = item.title
        holder.tvNewsAuthor.text = item.author
        holder.tvNewsDate.text = item.publishedAt.getFormattedDate()
        holder.tvNewsUrl.text = item.url
        Glide.with(context)
            .load(item.urlToImage)
            .skipMemoryCache(false)
            .centerCrop()
            .into(holder.ivNewsTitle)
        holder.tvNewsUrl.setOnClickListener {
            onNewsUrlClick?.invoke(item.url)
        }
        holder.cvNews.setOnClickListener {
            onNewsClick?.invoke(item)
        }
    }

    override fun getItemCount() = alNews.size

    fun addData(alNews: ArrayList<Article>) {
        this.alNews = alNews
        notifyDataSetChanged()
    }
}