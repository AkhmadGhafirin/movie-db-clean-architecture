package com.cascer.movieappcleanarchitecture.ui.moviefavorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cascer.movieappcleanarchitecture.databinding.ActivityMovieFavoriteBinding
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.ui.moviedetail.MovieDetailActivity
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieFavoriteBinding
    private val viewModel: MovieFavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy { MovieFavoriteAdapter { toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupViewModel()
    }

    private fun toDetail(movie: Movie) {
        startActivity(Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_ID, movie.id)
        })
    }

    private fun setupViews() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }
            rvList.apply {
                layoutManager = GridLayoutManager(
                    this@MovieFavoriteActivity, 2, GridLayoutManager.VERTICAL, false
                )
                adapter = favoriteAdapter
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            favoriteMovies.observe(this@MovieFavoriteActivity) {
                binding.progressBar.gone()
                if (it.isEmpty()) {
                    binding.containerEmpty.viewEmpty.visible()
                    binding.rvList.gone()
                } else {
                    binding.containerEmpty.viewEmpty.gone()
                    binding.rvList.visible()
                }
                favoriteAdapter.submitData(it)
            }
        }
    }
}