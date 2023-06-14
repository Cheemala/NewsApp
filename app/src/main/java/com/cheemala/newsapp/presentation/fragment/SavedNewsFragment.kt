package com.cheemala.newsapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheemala.newsapp.R
import com.cheemala.newsapp.data.model.Article
import com.cheemala.newsapp.databinding.FragmentSavedNewsBinding
import com.cheemala.newsapp.presentation.adapter.NewsAdapter
import com.cheemala.newsapp.presentation.screen.InfoActivity
import com.cheemala.newsapp.presentation.screen.MainActivity
import com.cheemala.newsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class SavedNewsFragment : Fragment() {
    private lateinit var fragmentSavedNewsBinding: FragmentSavedNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSavedNewsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_saved_news,container,false)
        // Inflate the layout for this fragment
        return fragmentSavedNewsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            try {
                Log.i("My_Check_", it.url!!)
                val moveToInfoScreen: Intent = Intent(activity, InfoActivity::class.java)
                moveToInfoScreen.putExtra("selected_article",it)
                startActivity(moveToInfoScreen)
                /*val bundle = Bundle().apply {
                        putSerializable("selected_article",it)
                    }
                    val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToInfoFragment(it)
                    findNavController().navigateUp()
                    findNavController().navigate(action)*/
            } catch (ex: java.lang.Exception) {
                Log.e("My_Check_error_", ex.message.toString())
            }

        }
        initRecyclerVw()
        viewNewsList()
    }

    private fun viewNewsList() {
        newsViewModel.getSavedNewsArticles().observe(viewLifecycleOwner, Observer {
            Log.i("Test_Data_", it.toString())
            newsAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerVw() {
        fragmentSavedNewsBinding.savedRcrVw.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletableArticle: Article =
                    newsAdapter.getItemByPosition(viewHolder.adapterPosition)
                newsViewModel.deleteSavedNewsArticle(deletableArticle)
                // below line is to notify our item is removed from adapter.
                // courseRVAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                Snackbar.make(
                    fragmentSavedNewsBinding.root,
                    "Deleted " + deletableArticle.title,
                    Snackbar.LENGTH_LONG
                ).show()
                /*.setAction(
                    "Undo",
                    View.OnClickListener {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        courseList.add(position, deletedCourse)

                        // below line is to notify item is
                        // added to our adapter class.
                        courseRVAdapter.notifyItemInserted(position)
                    }).show()*/
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(fragmentSavedNewsBinding.savedRcrVw)

    }
}