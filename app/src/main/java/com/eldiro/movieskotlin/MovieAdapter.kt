package com.eldiro.movieskotlin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eldiro.movieskotlin.models.Movies
import com.bumptech.glide.Glide
import com.eldiro.movieskotlin.databinding.MovieItemBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Locale


class MovieAdapter(private val movies: List<Movies>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

        val gson = Gson()
        val movieJson = gson.toJson(movie)

        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("MOVIE_OBJECT_JSON", movieJson) // Pass any necessary data to detail screen

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            val imageUrl = "https://image.tmdb.org/t/p/w185/${movie.posterPath}"
            val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)

            binding.movieTitleTextView.text = movie.title
            binding.movieDateTextView.text = outputFormat.format(movie.releaseDate)
            binding.movieDescTextView.text = movie.overview

            Glide.with(binding.root.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(binding.moviePosterImageView)
        }
    }
}