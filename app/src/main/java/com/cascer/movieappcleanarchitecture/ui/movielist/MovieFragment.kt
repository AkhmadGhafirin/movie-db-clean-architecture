package com.cascer.movieappcleanarchitecture.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.cascer.movieappcleanarchitecture.databinding.FragmentMovieBinding
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.ui.LoadingStateAdapter
import com.cascer.movieappcleanarchitecture.ui.moviedetail.MovieDetailActivity
import com.cascer.movieappcleanarchitecture.utils.Constant.NOW_PLAYING
import com.cascer.movieappcleanarchitecture.utils.Constant.POPULAR
import com.cascer.movieappcleanarchitecture.utils.Constant.TOP_RATED
import com.cascer.movieappcleanarchitecture.utils.Constant.UPCOMING
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment(private val type: Int) : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter { toDetail(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModel()
    }

    private fun toDetail(movie: Movie) {
        startActivity(Intent(requireContext(), MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_ID, movie.id)
        })
    }

    private fun setupViews() {
        with(binding) {
            rvList.apply {
                layoutManager =
                    GridLayoutManager(
                        requireContext(), 2, GridLayoutManager.VERTICAL, false
                    )
                adapter = movieAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter { movieAdapter.retry() }
                )
            }

            movieAdapter.addLoadStateListener { loadState ->
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                if (errorState != null) {
                    containerError.viewError.visible()
                    containerError.btnRetry.setOnClickListener {
                        movieAdapter.refresh()
                    }
                } else {
                    containerError.viewError.gone()
                    if (loadState.source.refresh is LoadState.Loading) {
                        if (!progressBar.isShimmerStarted) progressBar.startShimmer()
                        progressBar.visible()
                    } else {
                        progressBar.gone()
                        if (movieAdapter.itemCount == 0) {
                            containerEmpty.viewEmpty.visible()
                            rvList.gone()
                        } else {
                            containerEmpty.viewEmpty.gone()
                            rvList.visible()
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            when (type) {
                NOW_PLAYING -> {
                    nowPlayingMovies.observe(requireActivity()) {
                        movieAdapter.submitData(lifecycle, it)
                    }
                }

                POPULAR -> {
                    popularMovies.observe(requireActivity()) {
                        movieAdapter.submitData(lifecycle, it)
                    }
                }

                TOP_RATED -> {
                    topRatedMovies.observe(requireActivity()) {
                        movieAdapter.submitData(lifecycle, it)
                    }
                }

                UPCOMING -> {
                    upcomingMovies.observe(requireActivity()) {
                        movieAdapter.submitData(lifecycle, it)
                    }
                }
            }
        }
    }
}