package com.azrinurvani.mobile.learncleanarchitecture.helpers

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.azrinurvani.mobile.learncleanarchitecture.data.model.ResponseEverything
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun mapperResponseToDomain(response : ResponseEverything) : List<News>{
    val mutableList : MutableList<News> = mutableListOf()
    response.articles?.forEach {
        News(
            id = 0,
            name = it?.source?.name ?: "",
            title = it?.title ?: "",
            description = it?.description ?: "",
            url = it?.url ?: "",
            urlToImage = it?.urlToImage ?: "",
            publishedAt = it?.publishedAt ?: ""
        ).let(mutableList::add)
    }
    return mutableList

}

fun loadImageBitmap(context: Context,placeholder: Int,uri : String,imageView: ImageView){
    Glide.with(context)
        .asBitmap()
        .placeholder(placeholder)
        .apply(RequestOptions().centerCrop())
        .load(uri)
        .into(imageView)
}

fun showErrorMessage(context: Context, message:String) {
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

fun hideKeyboard(context: Context,searchView: SearchView) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
}