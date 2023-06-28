package com.djr.newssphere.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djr.newssphere.R
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.databinding.ItemHeadlineBinding

class HeadlineAdapter(private val onItemClick: (Headline) -> Unit) :
    ListAdapter<Headline, HeadlineAdapter.HeadlineViewHolder>(HeadlineDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeadlineBinding.inflate(inflater, parent, false)
        return HeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val headline = getItem(position)
        holder.bind(headline)
        holder.itemView.setOnClickListener { onItemClick(headline) }
    }

    class HeadlineViewHolder(val binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(headline: Headline) {
            binding.titleTextView.text = headline.title
            binding.descriptionTextView.text = headline.description

            Glide.with(itemView)
                .load(headline.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(binding.headlineImageView)
        }
    }

    class HeadlineDiffCallback : DiffUtil.ItemCallback<Headline>() {
        override fun areItemsTheSame(oldItem: Headline, newItem: Headline): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Headline, newItem: Headline): Boolean {
            return oldItem == newItem
        }
    }
}
