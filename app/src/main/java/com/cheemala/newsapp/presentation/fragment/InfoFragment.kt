package com.cheemala.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.cheemala.newsapp.R
import com.cheemala.newsapp.databinding.FragmentInfoBinding
import com.cheemala.newsapp.presentation.screen.MainActivity
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBindingObj:FragmentInfoBinding
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInfoBindingObj = DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false)
        // Inflate the layout for this fragment
        return fragmentInfoBindingObj.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as MainActivity).newsViewModel
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        fragmentInfoBindingObj.webVw.apply {
            webViewClient = WebViewClient()
            if (article.url != null && article.url!!.isNotEmpty())
                loadUrl(article.url!!)
        }

        fragmentInfoBindingObj.fabSave.setOnClickListener{
            if (article != null){
                newsViewModel.saveNewsArticle(article)
                Snackbar.make(view,"Saved successfully!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}