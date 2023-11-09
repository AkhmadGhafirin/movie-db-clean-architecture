package com.cascer.movieappcleanarchitecture.utils

import android.view.View
import java.text.SimpleDateFormat

fun View.visible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility =
        View.VISIBLE
}

fun View.gone() {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible() {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}

fun String.toDisplayDate(): String {
    return try {
        val apiFormat = SimpleDateFormat("yyyy-MM-ddTHH:mm:ss:SSSZ")
        val displayFormat = SimpleDateFormat("MM/dd/yyyy - HH:mm")
        val parseDate = apiFormat.parse(this)
        displayFormat.format(parseDate)
    } catch (e: Exception) {
        this
    }
}