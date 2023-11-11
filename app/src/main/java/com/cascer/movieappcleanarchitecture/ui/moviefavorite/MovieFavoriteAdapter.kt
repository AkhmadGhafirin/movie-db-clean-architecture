package com.cascer.movieappcleanarchitecture.ui.moviefavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cascer.movieappcleanarchitecture.databinding.ViewMovieBinding
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.utils.ImageUtils.load

class MovieFavoriteAdapter(
    private val listener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder>() {

    private var listItem = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        return MovieFavoriteViewHolder(
            ViewMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        listItem[position].let { holder.bind(it) }
    }

    fun submitData(data: List<Movie>) {
        val diffCallback = movieDiffCallback(listItem, data)
        val diffMovie = DiffUtil.calculateDiff(diffCallback)
        listItem.clear()
        listItem.addAll(data)
        diffMovie.dispatchUpdatesTo(this)
    }

    inner class MovieFavoriteViewHolder(private val binding: ViewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.invoke(listItem[bindingAdapterPosition])
            }
        }

        fun bind(item: Movie) {
            with(binding) {
                ivMovie.load(binding.root.context, item.posterPath)
                tvTitle.text = item.title
            }
        }
    }

    private fun movieDiffCallback(oldList: List<Movie>, newList: List<Movie>) =
        object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }
        }
}