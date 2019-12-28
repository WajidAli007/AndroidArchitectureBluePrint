package com.blueprint.architecture.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blueprint.architecture.myapplication.base.BaseFragment
import com.blueprint.architecture.myapplication.ui.movielist.MovieListFragment.Companion.newInstance

/**
 * Created by Wajid Ali
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        if (savedInstanceState == null) {
            redirectToPopularMovies()
        }
    }

    private fun redirectToMovieDetails(movieId : Int){

    }

    private fun redirectToPopularMovies(){
        loadFragment(newInstance())
    }

    fun loadFragment(fragment : BaseFragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
    }
}