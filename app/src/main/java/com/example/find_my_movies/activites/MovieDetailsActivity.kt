package com.example.find_my_movies.activites

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.find_my_movies.R
import com.example.find_my_movies.databinding.ActivityMovieDetailsBinding
import helper.Constants
import helper.loadImage
import viewModels.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val movieDetailsViewModel = MovieDetailsViewModel()
    private var mediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // View binding
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize media controller
        mediaController = MediaController(this)
        val titleId = intent.getStringExtra(Constants.TITLE_ID)
        if (titleId != null) {
            renderMovieDetails(titleId)
        }
    }

    /**
     * Render all the data related to the movie like image, name, release data, descriptions
     * @param titleId movie titleId that used to fetch all the movie data
     */
    private fun renderMovieDetails(titleId: String) {
        movieDetailsViewModel.fetchMovieDetails(titleId).observe(this) { titleResponse ->
            val description =
                titleResponse.featuredReviews.edges.first().node.text.originalText.plainText
            val imageUrl = titleResponse.primaryImage.url
            val releaseDate = titleResponse.releaseDate.let {
                "${it.day}/ ${it.month}/ ${it.year}"
            }
            val trailerVideoUrl =
                titleResponse.primaryVideos.edges.first().node.playbackURLs.first().url
            val movieTitle = titleResponse.titleText.text
            val movieRating = titleResponse.ratingsSummary.aggregateRating
            binding.descriptionTv.text = description
            binding.releaseYearTv.text = releaseDate
            binding.movieImageIv.loadImage(imageUrl)
            binding.movieNameTv.text = movieTitle
            binding.runTimeTv.text = "$movieRating"
            setUpVideoView(trailerVideoUrl)
        }
    }

    /**
     * Play video using the url in video view tag
     * @param videoUrl string url that contain video
     */
    private fun setUpVideoView(videoUrl: String) {
        val uri = Uri.parse(videoUrl)
        val videoView = binding.videoVv
        videoView.setVideoURI(uri)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaController?.removeAllViews()
    }
}