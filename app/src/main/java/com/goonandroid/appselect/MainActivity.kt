package com.goonandroid.appselect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goonandroid.appselect.databinding.ActivityMainBinding
import com.goonandroid.appselect.servicesapi.MovieApiInterface
import com.goonandroid.appselect.servicesapi.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : MovieAdapter
    // private var results = mutableListOf<Movie>()
   // private var pageNum = 0
    //var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMoviesList.layoutManager = layoutManager
        binding.rvMoviesList.setHasFixedSize(true)
//        adapter = MovieAdapter(results)
//        binding.rvMoviesList.adapter = adapter
        getMovieData { movies: List<Movie> ->
            adapter = MovieAdapter(movies)
            binding.rvMoviesList.adapter = adapter

        }
    }
    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                return callback(response.body()!!.movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })

    }
}

//        getMovies()
//
//        binding.rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val visibleItemCount = layoutManager.childCount
//                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
//                val total = adapter.itemCount
//
//                if (!isLoading) {
//
//                    if ((visibleItemCount + pastVisibleItem) >= total) {
//                        pageNum++
//                        getMovies()
//                    }
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
//
//
//    }
//    private fun getMovies() {
//        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
//        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
//            //@SuppressLint("NotifyDataSetChanged")
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                val m = response.body()
//                if (m != null) {
//                    val movie = m.movies[pageNum]
//                    results.addAll(listOf(movie))
//                    //results.addAll(results.)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable)  {
//            }
//        })
//    }
//}

