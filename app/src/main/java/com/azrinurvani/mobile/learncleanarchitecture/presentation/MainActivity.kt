package com.azrinurvani.mobile.learncleanarchitecture.presentation

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azrinurvani.mobile.learncleanarchitecture.R
import com.azrinurvani.mobile.learncleanarchitecture.data.Resource
import com.azrinurvani.mobile.learncleanarchitecture.databinding.ActivityMainBinding
import com.azrinurvani.mobile.learncleanarchitecture.domain.model.News
import com.azrinurvani.mobile.learncleanarchitecture.helpers.hideKeyboard
import com.azrinurvani.mobile.learncleanarchitecture.helpers.showErrorMessage
import com.azrinurvani.mobile.learncleanarchitecture.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSearchNews()
    }

    private fun initSearchNews(){
        binding.searchView.apply {
            queryHint = getString(R.string.search)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    retrieveSearchNews(query.toString())
                    hideKeyboard(this@MainActivity,binding.searchView)
                    return true

                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    retrieveSearchNews(newText.toString())
                    return false
                }


            })
        }
    }

    private fun retrieveSearchNews(query:String){
        viewModel.getNewsEverything(query).observe(this@MainActivity){ result ->
            when(result){
                is Resource.Error -> {
                    showErrorMessage(this@MainActivity,result.message.toString())
                }
                is Resource.Loading ->{

                }
                is Resource.Success ->{
                    if (!result.data.isNullOrEmpty()){
                        mainAdapter.changeList(result.data as MutableList<News>)
                        binding.rvNewsEverything.apply {
                            visibility = View.VISIBLE
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = mainAdapter
                        }
                    }else{
                        //empty data
                    }
                }
            }
        }
    }
}

