package com.cascer.movieappcleanarchitecture.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cascer.movieappcleanarchitecture.databinding.ViewCastBinding
import com.cascer.movieappcleanarchitecture.domain.model.MovieCast
import com.cascer.movieappcleanarchitecture.utils.ImageUtils.loadCircle

class MovieCastAdapter : RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder>() {

    private val listItem = mutableListOf<MovieCast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        return MovieCastViewHolder(
            ViewCastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        listItem[position].let { holder.bind(it) }
    }

    fun submitData(data: List<MovieCast>) {
        val diffCallback = movieCastCallback(listItem, data)
        val diffMovieCast = DiffUtil.calculateDiff(diffCallback)
        listItem.clear()
        listItem.addAll(data)
        diffMovieCast.dispatchUpdatesTo(this)
    }

    inner class MovieCastViewHolder(private val binding: ViewCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieCast) {
            with(binding) {
                ivCast.loadCircle(binding.root.context, item.profilePath)
                tvName.text = item.name
                tvCharacter.text = item.character
            }
        }
    }

    private fun movieCastCallback(oldList: List<MovieCast>, newList: List<MovieCast>) =
        object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }
        }
}