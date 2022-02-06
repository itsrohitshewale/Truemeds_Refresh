package com.example.truemedstask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.truemedstask.R
import com.example.truemedstask.core.BindingViewHolder
import com.example.truemedstask.databinding.ArticleItemBinding
import com.example.truemedstask.model.Article
import com.example.truemedstask.viewmodel.MainActivityViewModel

class ArticlesAdapter (var items: List<Article>, val vm: MainActivityViewModel):
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.viewmodel = vm
    }

    class ArticleViewHolder(itemView: View) : BindingViewHolder<ArticleItemBinding>(itemView)

}