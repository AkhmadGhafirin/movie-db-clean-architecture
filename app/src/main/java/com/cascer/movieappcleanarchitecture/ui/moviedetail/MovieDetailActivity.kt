package com.cascer.movieappcleanarchitecture.ui.moviedetail

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.movieappcleanarchitecture.R
import com.cascer.movieappcleanarchitecture.data.Resource
import com.cascer.movieappcleanarchitecture.databinding.ActivityMovieDetailBinding
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.ui.moviereview.MovieReviewActivity
import com.cascer.movieappcleanarchitecture.utils.ImageUtils.load
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.toDisplayDate
import com.cascer.movieappcleanarchitecture.utils.toDisplayTime
import com.cascer.movieappcleanarchitecture.utils.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private val castAdapter by lazy { MovieCastAdapter() }
    private var id = 0
    private var youTubePlayer: YouTubePlayer? = null
    private var isFavorite = false
    private var firstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra(EXTRA_ID, 0)
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        with(binding) {
            toolbar.apply {
                setNavigationOnClickListener {
                    finish()
                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.reviews -> {
                            startActivity(
                                Intent(
                                    this@MovieDetailActivity,
                                    MovieReviewActivity::class.java
                                ).apply {
                                    putExtra(EXTRA_ID, this@MovieDetailActivity.id)
                                })
                            true
                        }

                        else -> false
                    }
                }
            }

            rvCast.apply {
                layoutManager = LinearLayoutManager(
                    this@MovieDetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = castAdapter
            }
        }
    }

    private fun setupVideo(videoId: String) {
        with(binding) {
            val youtubePlayerView = youtubePlayerView
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@MovieDetailActivity.youTubePlayer = youTubePlayer
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }
    }

    private fun setupDataView(movie: Movie) {
        with(binding) {
            ivMovieDetail.load(this@MovieDetailActivity, movie.posterPath)
            tvTitle.text = movie.title
            tvRelease.text = movie.releaseDate.toDisplayDate(
                apiFormat = "yyyy-MM-dd",
                displayFormat = "MMM dd, yyyy"
            )
            tvRating.text = movie.voteAverage.toString()
            tvOverview.text = movie.overview
            tvDuration.text = movie.runtime.toDisplayTime()
            tvGenres.text = TextUtils.join(", ", movie.genres.map { it.name })
            tvProductionCompanies.text =
                TextUtils.join(", ", movie.productionCompanies.map { it.name })
            tvProductionCountries.text =
                TextUtils.join(", ", movie.productionCountries.map { it.name })
            tvLanguages.text = TextUtils.join(", ", movie.spokenLanguages.map { it.name })

            fabFavorite.setOnClickListener { _ ->
                isFavorite = !isFavorite
                val message = if (isFavorite) getString(R.string.add_favorite)
                else getString(R.string.remove_favorite)
                Toast.makeText(this@MovieDetailActivity, message, Toast.LENGTH_SHORT).show()
                viewModel.addFavorite(movie, isFavorite)
                setStatusFavorite()
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            detailMovie(id).observe(this@MovieDetailActivity) {
                if (it != null) {
                    when (it) {
                        is Resource.Success -> {
                            handleLoadingState(false)
                            it.data?.let { movie -> setupDataView(movie) }
                        }

                        is Resource.Loading -> {
                            handleLoadingState(true)
                        }

                        is Resource.Error -> {
                            handleLoadingState(false)
                            Toast.makeText(this@MovieDetailActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }
            }
            castMovie(id).observe(this@MovieDetailActivity) {
                if (it != null) {
                    when (it) {
                        is Resource.Success -> {
                            handleLoadingState(false)
                            it.data?.let { movies -> castAdapter.submitData(movies) }
                        }

                        is Resource.Loading -> {
                            handleLoadingState(true)
                        }

                        is Resource.Error -> {
                            handleLoadingState(false)
                            Toast.makeText(this@MovieDetailActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            videoMovie(id).observe(this@MovieDetailActivity) {
                if (it != null) {
                    when (it) {
                        is Resource.Success -> {
                            val videos =
                                it.data?.filter { video -> video.official && video.site == "YouTube" && video.type == "Trailer" }
                            videos?.first()?.let { video -> setupVideo(video.key) }
                        }

                        is Resource.Loading -> {}

                        is Resource.Error -> {
                            Toast.makeText(this@MovieDetailActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            statusFavorite(id).observe(this@MovieDetailActivity) {
                isFavorite = it.id != 0
                if (firstLoad) {
                    firstLoad = false
                    setStatusFavorite()
                }
            }
        }
    }

    private fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visible()
            binding.containerDetail.gone()
        } else {
            binding.containerDetail.visible()
            binding.progressBar.gone()
        }
    }

    private fun setStatusFavorite() {
        if (isFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}