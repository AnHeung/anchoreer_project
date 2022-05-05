package com.anchoreer.presentation.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.ItemMovieBinding


class MovieAdapter(val context: Context, val listener: MovieItemClickListener) :
    ListAdapter<MovieItem, MovieAdapter.MovieViewHolder>(MovieDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun clearItems() {
        submitList(null)
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem: MovieItem) {
            binding.apply {
                item = movieItem
                itemClickListener = listener
            }
        }
    }


    object MovieDiff : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }

    interface MovieItemClickListener {
        fun itemClick(item: MovieItem)
        fun bookmarkClick(item: MovieItem)
    }
}