package com.goonandroid.appselect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.goonandroid.appselect.adapter.MovieAdapter
import com.goonandroid.appselect.databinding.ActivityMainBinding
import com.goonandroid.appselect.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        movieAdapter = MovieAdapter()
        setContentView(binding.root)
        binding.rvMoviesList.apply {
            adapter = movieAdapter
            val layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            binding.rvMoviesList.layoutManager = layoutManager
            binding.rvMoviesList.setHasFixedSize(true)

        }
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                movieAdapter.submitData(it)

            }
        }
    }
}


