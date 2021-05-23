package com.adrian.movieapp.presentation.movielist.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adrian.movieapp.R
import com.adrian.movieapp.data.repository.MovieRepository
import com.adrian.movieapp.presentation.movielist.viewmodel.MovieListViewModel
import com.adrian.movieapp.presentation.movielist.viewmodel.MovieListViewModelFactory
import java.util.concurrent.Executors

class MovieListActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        viewModel.getPopularMovies()
    }

    private fun init() {
        val repository = MovieRepository(Executors.newSingleThreadExecutor(), Handler(Looper.getMainLooper()))
        val viewModelProvider = ViewModelProvider(this, MovieListViewModelFactory(repository))
        viewModel = viewModelProvider.get(MovieListViewModel::class.java)

        viewModelObserve()
    }

    private fun viewModelObserve() {
        viewModel.movieLiveData.observe(this, {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        })
    }
}