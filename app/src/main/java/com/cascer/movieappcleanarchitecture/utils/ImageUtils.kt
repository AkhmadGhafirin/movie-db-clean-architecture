package com.cascer.movieappcleanarchitecture.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Random

object ImageUtils {
    fun ImageView.load(context: Context, url: String) {
        Glide.with(context)
            .load(url.trim())
            .into(this)
    }

    fun ImageView.loadCircle(context: Context, url: String) {
        Glide.with(context)
            .load(url.trim())
            .circleCrop()
            .into(this)
    }

    fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}