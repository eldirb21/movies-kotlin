package com.eldiro.movieskotlin.models

import com.eldiro.movieskotlin.services.DateDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Movies(
      val id: Int,
      val title: String,
      var overview:String,
      @SerializedName("genre_ids") val genreIds: Array<Int>,
      @SerializedName("original_language") val originalLanguage:String,
      @SerializedName("original_title") val originalTitle:String,
      val adult:Boolean,
      val popularity : Float,
//      @JsonAdapter(DateDeserializer::class)
      @SerializedName("release_date") val releaseDate: Date,
      val video: Boolean,
      @SerializedName("vote_average") val voteAverage : Float,
      @SerializedName("vote_count") val voteCount: Float,
      @SerializedName("backdrop_path") val backdropPath: String,
      @SerializedName("poster_path") val posterPath: String
)
data class MovieResponse(
    val results: List<Movies>
)