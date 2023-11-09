package com.cascer.movieappcleanarchitecture.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cascer.movieappcleanarchitecture.R
import com.cascer.movieappcleanarchitecture.databinding.ActivityMainBinding
import com.cascer.movieappcleanarchitecture.ui.moviefavorite.MovieFavoriteActivity
import com.cascer.movieappcleanarchitecture.ui.movielist.MovieFragment
import com.cascer.movieappcleanarchitecture.utils.Constant.NOW_PLAYING
import com.cascer.movieappcleanarchitecture.utils.Constant.POPULAR
import com.cascer.movieappcleanarchitecture.utils.Constant.TOP_RATED
import com.cascer.movieappcleanarchitecture.utils.Constant.UPCOMING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        replaceFragment(MovieFragment(NOW_PLAYING))
        with(binding) {
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.favorites -> {
                        startActivity(Intent(this@MainActivity, MovieFavoriteActivity::class.java))
                        true
                    }

                    else -> false
                }
            }

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.now_playing -> {
                        replaceFragment(MovieFragment(NOW_PLAYING))
                        true
                    }

                    R.id.popular -> {
                        replaceFragment(MovieFragment(POPULAR))
                        true
                    }

                    R.id.top_rated -> {
                        replaceFragment(MovieFragment(TOP_RATED))
                        true
                    }

                    R.id.upcoming -> {
                        replaceFragment(MovieFragment(UPCOMING))
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}