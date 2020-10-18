package com.example.tandemtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tandemtest.R
import kotlinx.android.synthetic.main.profile_load_state_footer.view.*

class HomeLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<HomeLoadStateAdapter.LoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.profile_load_state_footer, parent, false)
        return LoadStateViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.button_retry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            itemView.progress_bar.isVisible = loadState is LoadState.Loading
            itemView.button_retry.isVisible = loadState is LoadState.Error
            itemView.text_view_error.isVisible = loadState is LoadState.Error
        }
    }
}