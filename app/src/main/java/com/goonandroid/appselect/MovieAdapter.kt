package com.goonandroid.appselect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goonandroid.appselect.databinding.MovieItemBinding

class MovieAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = MovieItemBinding.bind(view)

        fun bindMovie(movie: Movie) = with(binding){
            movieTitle.text = movie.title
            movieDescription.text = movie.description
            Glide.with(itemView).load(movie.multimedia!!.poster).into(moviePoster)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))

    }

    override fun getItemCount(): Int = movies.size

}