package com.example.truemedstask.bindingadapter

import com.example.truemedstask.model.Article
import com.example.truemedstask.viewmodel.MainActivityViewModel
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.truemedstask.adapter.ArticlesAdapter

@BindingAdapter(value = ["repositories", "viewModel"])
fun setArticle(view: RecyclerView, items: List<Article>, viewmodel: MainActivityViewModel) {
    view.adapter?.run {
        if (this is ArticlesAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        ArticlesAdapter(items, viewmodel).apply { view.adapter = this }
    }
}