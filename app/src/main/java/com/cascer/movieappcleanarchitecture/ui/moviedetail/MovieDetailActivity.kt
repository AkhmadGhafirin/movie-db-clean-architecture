package com.cascer.movieappcleanarchitecture.ui.moviedetail

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.map
import com.cascer.movieappcleanarchitecture.data.Resource
import com.cascer.movieappcleanarchitecture.databinding.ActivityMovieDetailBinding
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.utils.ImageUtils.load
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra(EXTRA_ID, 0)
        setupViewModel()
    }

    private fun setupDataView(movie: Movie) {
        with(binding) {
            ivMovieDetail.load(this@MovieDetailActivity, movie.posterPath)
            tvTitle.text = movie.title
            tvRelease.text = movie.releaseDate
            tvRating.text = movie.voteAverage.toString()
            tvOverview.text = movie.overview
            tvGenres.text = TextUtils.join(", ", movie.genres.map { it.name })
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            detailMovie(id).observe(this@MovieDetailActivity) {
                if (it != null) {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressbar.gone()
                            it.data?.let { movie -> setupDataView(movie) }
                        }

                        is Resource.Loading -> {
                            binding.progressbar.visible()
                        }

                        is Resource.Error -> {
                            Toast.makeText(this@MovieDetailActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            videoMovie(id).observe(this@MovieDetailActivity) {
                Log.d("VIDEOMOVIE", "setupViewModel: ${it.map { movie -> movie.toString() }}")

            }
            reviewMovie(id).observe(this@MovieDetailActivity) {
                Log.d("REVIEWMOVIE", "setupViewModel: ${it.map { movie -> movie.toString() }}")
            }
        }
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}