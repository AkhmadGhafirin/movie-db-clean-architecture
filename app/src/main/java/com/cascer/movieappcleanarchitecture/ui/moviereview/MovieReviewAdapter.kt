package com.cascer.movieappcleanarchitecture.ui.moviereview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cascer.movieappcleanarchitecture.R
import com.cascer.movieappcleanarchitecture.databinding.ViewReviewBinding
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.utils.ImageUtils.loadCircle
import com.cascer.movieappcleanarchitecture.utils.gone
import com.cascer.movieappcleanarchitecture.utils.toDisplayDate
import com.cascer.movieappcleanarchitecture.utils.visible

class MovieReviewAdapter(
    private val listener: (review: MovieReview) -> Unit
) : PagingDataAdapter<MovieReview, MovieReviewAdapter.MovieReviewViewHolder>(DIFF_REVIEW_ITEM_CALLBACK) {

    private var isOpen = false

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            ViewReviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    inner class MovieReviewViewHolder(private val binding: ViewReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { item -> listener.invoke(item) }
            }
        }

        fun bind(item: MovieReview) {
            with(binding) {
                ivAvatar.loadCircle(binding.root.context, item.authorDetails.avatarPath)
                tvReviewer.text = item.author
                tvDate.text = item.createdAt.toDisplayDate()
                tvRating.text = item.authorDetails.rating.toString()
                tvContent.text = item.content
                rowContainer.setOnClickListener {
                    isOpen = !isOpen
                    if (isOpen) {
                        ivCollapsing.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.baseline_keyboard_arrow_down_24
                            )
                        )
                        tvContent.gone()
                    } else {
                        ivCollapsing.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.baseline_keyboard_arrow_up_24
                            )
                        )
                        tvContent.visible()
                    }
                }
            }
        }
    }

    companion object {
        val DIFF_REVIEW_ITEM_CALLBACK = object : DiffUtil.ItemCallback<MovieReview>() {
            override fun areItemsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieReview, newItem: MovieReview): Boolean {
                return oldItem == newItem
            }

        }
    }

}