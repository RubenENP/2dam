package com.example.flowsrubenhita.ui

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import com.example.moviesrubenhita.domain.modelo.Movie
import com.example.moviesrubenhita.R
import com.example.moviesrubenhita.databinding.ItemMovieBinding
import androidx.transition.TransitionManager
import coil.load
import com.example.moviesrubenhita.data.Config

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie) = with(binding) {
            titleTv.text = item.title
            fechaTv.text = item.releaseDate
            ratingBar.rating = item.voteAverage.toFloat() / 2
            smallMovieImage.load(Config.IMAGE_URL + item.imgPath)
            movieImage.load(Config.IMAGE_URL + item.imgPath)

            cardView.setOnClickListener {
                val v: Int = if (linearDetails.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }

                layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

                TransitionManager.beginDelayedTransition(layout, AutoTransition())
                linearDetails.visibility = v

                val v1: Int = if (smallMovieImage.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }

                smallMovieImage.visibility = v1
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}