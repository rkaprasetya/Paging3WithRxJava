package com.example.tandemtest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.tandemtest.R
import com.example.tandemtest.data.model.Profile
import kotlinx.android.synthetic.main.item_user_profile.view.*

class HomeAdapter : PagingDataAdapter<Profile, HomeAdapter.ViewHolder>(PROFILE_COMPARATOR) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_user_profile, parent, false)
        return ViewHolder(
            itemView
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Profile) {
            itemView.image_profile.load(item.pictureUrl) {
                error(R.drawable.ic_error)
                placeholder(R.drawable.ic_smile)

            }
            itemView.text_name.text = item.firstName
            itemView.text_description.text = item.topic
            setReference(itemView,item.referenceCnt?:0)
            itemView.text_native_value.text = getLanguages(item.natives)
            itemView.text_learns_value.text = getLanguages(item.learns)
        }

        private fun getLanguages(list:List<String?>?):String{
            var languages = ""
            list?.forEach { languages += " $it" }
            return languages
        }

        private fun setReference(itemView: View, reference: Int) {
            if (reference == 0) {
                itemView.text_reference_new.visibility = View.VISIBLE
                itemView.text_reference_counter.visibility = View.GONE
            } else {
                itemView.text_reference_new.visibility = View.GONE
                itemView.text_reference_counter.visibility = View.VISIBLE
                itemView.text_reference_counter.text = reference.toString()
            }
        }
    }

    companion object {
        private val PROFILE_COMPARATOR = object : DiffUtil.ItemCallback<Profile>() {
            override fun areItemsTheSame(oldItem: Profile, newItem: Profile) =
                oldItem.pictureUrl == newItem.pictureUrl

            override fun areContentsTheSame(oldItem: Profile, newItem: Profile) =
                oldItem == newItem
        }
    }
}