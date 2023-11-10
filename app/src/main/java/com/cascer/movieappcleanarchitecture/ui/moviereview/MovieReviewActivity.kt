package com.cascer.movieappcleanarchitecture.ui.moviereview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.cascer.movieappcleanarchitecture.databinding.ActivityMovieReviewBinding
import com.cascer.movieappcleanarchitecture.ui.LoadingStateAdapter
import com.cascer.movieappcleanarchitecture.ui.moviedetail.MovieDetailActivity
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieReviewBinding
    private val viewModel: MovieReviewViewModel by viewModels()
    private val reviewAdapter by lazy { MovieReviewAdapter {} }
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra(MovieDetailActivity.EXTRA_ID, 0)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }
            rvList.apply {
                layoutManager = LinearLayoutManager(
                    this@MovieReviewActivity, LinearLayoutManager.VERTICAL, false
                )
                adapter =
                    reviewAdapter.withLoadStateFooter(footer = LoadingStateAdapter { reviewAdapter.retry() })
            }

            swipeRefresh.setOnRefreshListener {
                rvList.gone()
                reviewAdapter.refresh()
            }

            reviewAdapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.Loading) {
                    if (!progressBar.isShimmerStarted) progressBar.startShimmer()
                    progressBar.visible()
                } else {
                    progressBar.gone()
                    if (reviewAdapter.itemCount == 0) {
                        swipeRefresh.gone()
                        containerEmpty.viewEmpty.visible()
                        rvList.gone()
                    } else {
                        swipeRefresh.isRefreshing = false
                        swipeRefresh.visible()
                        containerEmpty.viewEmpty.gone()
                        rvList.visible()
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            reviewMovie(id).observe(this@MovieReviewActivity) {
                reviewAdapter.submitData(lifecycle, it)
            }
        }
    }
}