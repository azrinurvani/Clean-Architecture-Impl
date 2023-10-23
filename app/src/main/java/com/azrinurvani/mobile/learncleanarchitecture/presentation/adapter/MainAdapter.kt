package com.azrinurvani.mobile.learncleanarchitecture.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrinurvani.mobile.learncleanarchitecture.R
import com.azrinurvani.mobile.learncleanarchitecture.databinding.ItemNewsEverythingBinding
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import com.azrinurvani.mobile.learncleanarchitecture.helpers.loadImageBitmap
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.MainViewHolder>()  {

    private var newsList : MutableList<News> = mutableListOf()

    inner class MainViewHolder(private val binding: ItemNewsEverythingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(data : News){
            with(binding){
                loadImageBitmap(
                    context = itemView.context,
                    placeholder = R.drawable.ic_launcher_background,
                    imageView = imgContent,
                    uri = data.urlToImage
                )
                tvTitle.text = data.title
                tvDescription.text = data.description
                tvDateTime.text = data.publishedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater =  ItemNewsEverythingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(inflater)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        newsList[position].let { data ->
            holder.bindItem(data)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(data : MutableList<News>){
        newsList = data
        notifyDataSetChanged()
    }
}