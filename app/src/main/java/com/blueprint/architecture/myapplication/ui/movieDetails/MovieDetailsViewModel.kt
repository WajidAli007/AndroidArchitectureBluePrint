package com.blueprint.architecture.myapplication.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blueprint.architecture.myapplication.model.movieDetails.MovieDetails
import com.blueprint.architecture.myapplication.repository.MovieRepository


/**
 * Created by Wajid Ali on 2019-12-29.
 */

class MovieDetailsViewModel : ViewModel() {

    var movies: MutableLiveData<MovieDetails?> = MutableLiveData()
    private var pageNumber = 0
    private val movieRepository : MovieRepository = MovieRepository()

    fun getPopularMovies(movieId : Int) {
        movieRepository.getMovieDetails(movieId).observeForever{
            movies.value = it
        }
    }

}