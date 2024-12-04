package com.example.find_my_movies.activites

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.find_my_movies.R
import helper.Loader
import recyclerView.MovieAdapter
import viewModels.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var searchBtn: ImageButton
    private lateinit var movieName: EditText
    private lateinit var noData: TextView
    private val searchViewModel: SearchViewModel = SearchViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchBtn = findViewById(R.id.search_btn)
        movieName = findViewById(R.id.movieName_edt)
        noData = findViewById(R.id.no_data_tv)

        // Initialise movie adapter
        val adapter = initialiseMovieAdapter()
        searchBtn.setOnClickListener {
            searchMovie(adapter)
        }
        movieName.setOnEditorActionListener { view, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                searchMovie(adapter)
            }
            false
        }
    }

    private fun searchMovie(adapter: MovieAdapter) {
        if (movieName.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter movie name", Toast.LENGTH_SHORT).show()
            return
        }
        renderMovies(movieName.text.toString(), adapter)
    }

    private fun renderMovies(query: String, adapter: MovieAdapter) {
        Loader.showLoader(this@SearchActivity, "Loading...", "Please wait movie are loading...")
        searchViewModel.fetchMovie(query).observe(this) { movieResponse ->
            noData.visibility = View.GONE
            if (movieResponse == null) {
                noDataView()
            }
            val movieList = movieResponse.titleResults.results
            adapter.setMovieList(movieList)
            Loader.hideLoader()
        }
    }

    private fun initialiseMovieAdapter(): MovieAdapter {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_movies)
        recyclerView.visibility = View.VISIBLE
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        val movieAdapter = MovieAdapter(this)
        recyclerView.adapter = movieAdapter
        return movieAdapter
    }

    private fun noDataView() {
        noData.visibility = View.VISIBLE
    }
}