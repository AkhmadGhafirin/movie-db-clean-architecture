package com.cascer.movieappcleanarchitecture.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.cascer.movieappcleanarchitecture.databinding.FragmentMovieBinding
import com.cascer.movieappcleanarchitecture.utils.Constant.FAVORITE
import com.cascer.movieappcleanarchitecture.utils.Constant.NOW_PLAYING
import com.cascer.movieappcleanarchitecture.utils.Constant.POPULAR
import com.cascer.movieappcleanarchitecture.utils.Constant.TOP_RATED
import com.cascer.movieappcleanarchitecture.utils.Constant.UPCOMING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment(private val type: Int) : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter { } }

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

                FAVORITE -> {
                    upcomingMovies.observe(requireActivity()) {
                        movieAdapter.submitData(lifecycle, it)
                    }
                }
            }
        }
    }
}