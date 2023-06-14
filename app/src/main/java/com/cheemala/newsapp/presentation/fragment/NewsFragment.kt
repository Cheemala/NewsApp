package com.cheemala.newsapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheemala.newsapp.R
import com.cheemala.newsapp.data.model.NewsResponse
import com.cheemala.newsapp.data.util.Resource
import com.cheemala.newsapp.databinding.FragmentNewsBinding
import com.cheemala.newsapp.presentation.adapter.NewsAdapter
import com.cheemala.newsapp.presentation.screen.InfoActivity
import com.cheemala.newsapp.presentation.screen.MainActivity
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        // Inflate the layout for this fragment
        return fragmentNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        fragmentNewsBinding.newsAdapter = newsAdapter
        newsAdapter.setOnItemClickListener {
            try {
                val moveToInfoScreen: Intent = Intent(activity, InfoActivity::class.java)
                moveToInfoScreen.putExtra("selected_article", it)
                startActivity(moveToInfoScreen)
            } catch (ex: java.lang.Exception) {
                Log.e("My_Check_error_", ex.message.toString())
            }

        }
        viewNewsList()
    }

    private fun viewNewsList() {
        newsViewModel.getNewsHeadlines("us", 1)
        newsViewModel.topNewsHeadlines.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { newsRes ->
                        newsAdapter.differ.submitList(newsRes.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { errMsg ->
                        Toast.makeText(activity, "Error Occured - $errMsg", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun showProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }


    private fun hideProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }

}