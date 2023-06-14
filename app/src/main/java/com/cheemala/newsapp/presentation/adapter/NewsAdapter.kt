package com.cheemala.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.databinding.NewsListItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.urlToImage == newItem.urlToImage
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindingObj =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bindingObj)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyViewHolder(val bindingObj: NewsListItemBinding) :
        RecyclerView.ViewHolder(bindingObj.root) {
        fun bind(article: Article) {
            bindingObj.newsArticle = article
            bindingObj.root.setOnClickListener{
                onItemclickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemclickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listner: ((Article) -> Unit)) {
        onItemclickListener = listner
    }

    fun getItemByPosition(position: Int):Article{
        return differ.currentList[position]
    }

}