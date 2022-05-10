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
    lateinit var adapter: MovieAdapter
    private var results = ArrayList<Movie>()
    private var pageNum = 0
    var isLoading = false
    var has_more = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMoviesList.layoutManager = layoutManager
        binding.rvMoviesList.setHasFixedSize(true)
        adapter = MovieAdapter(results)
        binding.rvMoviesList.adapter = adapter
//        getMovieData { movies: ArrayList<Movie> ->
//            binding.rvMoviesList.adapter = MovieAdapter(movies)
//
//        }
        getMovieData ()
        binding.rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                val lastvisibleitemposition: Int = layoutManager.findLastVisibleItemPosition()
//                val visibleItemCount = layoutManager.childCount
//                val firstVivisibleItems = layoutManager.findFirstVisibleItemPosition()
//                val total = adapter.itemCount

                //if (!binding.rvMoviesList.canScrollVertically(1) && has_more){
                if (has_more){
                        if (!isLoading) {
                            isLoading = true
                            pageNum += 20
                            getMovieData()
                            isLoading = false

                        }
                    }

                }

        })
    }


     private fun getMovieData() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList(pageNum).enqueue(object : Callback<MovieResponse> {

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {

                val m = response.body()
                if (m != null) {
                    val movie = m.movies
                    results.addAll(movie)
                    adapter = MovieAdapter(results)
                    binding.rvMoviesList.adapter = adapter
                    adapter.notifyDataSetChanged()
                    has_more = m.has_more
                }


            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })

    }
}
///////////New realization
//        binding.rvMoviesList.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
//            override fun isLastPage(): Boolean {
//                return isLastPage
//            }
//            override fun isLoading(): Boolean {
//                return isLoading
//            }
//            override fun loadMoreItems() {
//                isLoading = true
//                //you have to call loadmore items to get more data
////                val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
////                apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
////
////                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
////                val m = response.body()
////                if (m != null) {
////                    val movie = m.movies
////                    results.addAll(movie)
////
////                   }
////                }
////
////                  override fun onFailure(call: Call<MovieResponse>, t: Throwable)  {
////                }
////            })
//
//                getMoreItems()
//            }
//        })





/////////////////////////////////////////////
//        getMovies()

//        binding.rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val visibleItemCount = layoutManager.childCount
//                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
//                val total = adapter.itemCount
//
//                if (!isLoading) {
//
//                    if ((visibleItemCount + pastVisibleItem) >= total) {
//                        //pageNum++
//                        pageNum += 20
//                        getMovies()
//                    }
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
//        binding.rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val lastvisibleitemposition: Int = layoutManager.findLastVisibleItemPosition()
//                val visibleItemCount = layoutManager.childCount
//                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
//                val total = adapter.itemCount
//                currentPage ++
//                if (currentPage == total - 5) {
//                    if (!isLoading) {
//                        //if (currentPage == total) {
//                            isLoading = true
//                            pageNum += 20
//                            getMovies()
//                            // Увеличиваем на 1 pagecount при каждой прокрутке для получения данных со следующей страницы
//                            // make loading = false после загрузки данных
//                            // Вызовите mAdapter.notifyDataSetChanged (), чтобы обновить адаптер и макет
//                        //}
//                    }
//                }
//            }
//        })
//    }
//
//    private fun getMovies() {
//        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
//        apiService.getMovieList(pageNum).enqueue(object : Callback<MovieResponse>{
//            //@SuppressLint("NotifyDataSetChanged")
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                val m = response.body()
//                if (m != null) {
//                    val movie = m.movies
//                    results.addAll(movie)
//
//                    adapter = MovieAdapter(results)
//                    binding.rvMoviesList.adapter = adapter
//                    //adapter.notifyDataSetChanged()
//                    isLoading = false
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable)  {
//            }
//        })
//    }


//}


