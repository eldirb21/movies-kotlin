package com.eldiro.movieskotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.eldiro.movieskotlin.models.MovieResponse
import com.eldiro.movieskotlin.models.Movies
import com.eldiro.movieskotlin.services.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var apiServices: ApiServices
    private val baseUrl = "https://api.themoviedb.org/3/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)


        // Initialize views
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiServices = retrofit.create(ApiServices::class.java)

        swipeRefreshLayout.setOnRefreshListener {
            // This block will be executed when the user triggers a refresh
            fetchDataFromApi(recyclerView) // Call your method to fetch data from the API again
        }

        fetchDataFromApi(recyclerView)


    }

    private fun fetchDataFromApi(recyclerView: RecyclerView){
        val apiKey = "969025cbf23551ca12b9e12950987578" // Your API key
        val call:Call<MovieResponse> = apiServices.getDataFromApi(apiKey)

        call.enqueue(object :Callback<MovieResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    println(response.body())

                    val movies: List<Movies>? = response.body()?.results
                        movies?.let {
                            val adapter = MovieAdapter(it)
                            recyclerView.adapter = adapter
                        }
                    swipeRefreshLayout.isRefreshing = false
                }else{
                    println(response)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}