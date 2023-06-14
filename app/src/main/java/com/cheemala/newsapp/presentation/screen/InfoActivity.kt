package com.cheemala.newsapp.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cheemala.newsapp.R
import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.databinding.ActivityInfoBinding
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModel
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoActivity : AppCompatActivity() {
    private lateinit var activityInfoBinding: ActivityInfoBinding
    private lateinit var newsViewModel: NewsViewModel
    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInfoBinding = DataBindingUtil.setContentView(this,R.layout.activity_info)
        newsViewModel = ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]
        val selectedArticle = intent.getSerializableExtra("selected_article") as Article
        activityInfoBinding.webVw.apply {
            webViewClient = WebViewClient()
            if (selectedArticle.url != null && selectedArticle.url!!.isNotEmpty())
                loadUrl(selectedArticle.url!!)
        }

        activityInfoBinding.fabSave.setOnClickListener{
            if (selectedArticle != null){
                newsViewModel.saveNewsArticle(selectedArticle)
                Snackbar.make(activityInfoBinding.root,"Saved successfully!", Snackbar.LENGTH_LONG).show()
            }
        }

    }
}