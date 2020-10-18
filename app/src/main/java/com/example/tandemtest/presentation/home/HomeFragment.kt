package com.example.tandemtest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tandemtest.R
import com.example.tandemtest.presentation.home.adapter.HomeAdapter
import com.example.tandemtest.presentation.home.adapter.HomeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        setupObserver()
    }

    private fun init(view: View) {
        homeAdapter = HomeAdapter()
        recyclerView = view.findViewById(R.id.recycler_profile)
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerView.apply {
            layoutManager = mLayoutManager
            addItemDecoration(DividerItemDecoration(activity, mLayoutManager.orientation))
            setHasFixedSize(true)
            adapter = homeAdapter.withLoadStateHeaderAndFooter(
                header = HomeLoadStateAdapter { homeAdapter.retry() },
                footer = HomeLoadStateAdapter { homeAdapter.retry() }
            )
        }
        setLoadStateListener(view)
        view.button_retry_home.setOnClickListener { homeAdapter.retry() }
    }

    private fun setLoadStateListener(view: View) {
        homeAdapter.addLoadStateListener { loadState ->
            view.progress_bar_home.isVisible = loadState.source.refresh is LoadState.Loading
            view.recycler_profile.isVisible = loadState.source.refresh is LoadState.NotLoading
            view.button_retry_home.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun setupObserver() {
        viewModel.getProfiles().observe(viewLifecycleOwner) {
            homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}