package com.eldiro.movieskotlin

import android.media.tv.TvContract
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eldiro.movieskotlin.models.MovieResponse
import com.eldiro.movieskotlin.models.Movies
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.text.SimpleDateFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val spinner: Spinner = findViewById(R.id.movieOptionsSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.movie_options, // Reference the string array resource here
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
        }


        displayMovieDetails()
    }
    private fun displayMovieDetails() {
        val movieJson = intent.getStringExtra("MOVIE_OBJECT_JSON")
        val gson = Gson()
        val movie: Movies = gson.fromJson(movieJson, Movies::class.java)
        val imageUrl = "https://image.tmdb.org/t/p/w185/${movie.posterPath}"
        val imageUrlBg = "https://image.tmdb.org/t/p/w185/${movie.backdropPath}"
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)


        val titleTextView: TextView = findViewById(R.id.movieTitleTextView)
        titleTextView.text = movie.title

        val dateTextView: TextView = findViewById(R.id.movieDateTextView)
        dateTextView.text = outputFormat.format(movie.releaseDate)

        val descTextView: TextView = findViewById(R.id.movieDescTextView)
        descTextView.text = movie.overview



        val movieBgImageView: ImageView = findViewById(R.id.movieBackgroundImageView)
        Glide.with(this)
            .load(imageUrlBg) // Load image URL into ImageView
            .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
            .error(R.drawable.error_image) // Error image if loading fails
            .into(movieBgImageView)

        val moviePosterImageView: ImageView = findViewById(R.id.moviePosterImageView)
        Glide.with(this)
            .load(imageUrl) // Load image URL into ImageView
            .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
            .error(R.drawable.error_image) // Error image if loading fails
            .into(moviePosterImageView)
    }
}