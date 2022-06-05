package com.goonandroid.appselect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goonandroid.appselect.Movie
import com.goonandroid.appselect.R
import com.goonandroid.appselect.databinding.MovieItemBinding


class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)

        fun bindMovie(movie: Movie) = with(binding) {
            movieTitle.text = movie.title
            movieDescription.text = movie.description
            Glide.with(itemView).load(movie.multimedia!!.poster).into(moviePoster)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(getItem(position)!!)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}