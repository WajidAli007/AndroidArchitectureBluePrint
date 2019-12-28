package com.blueprint.architecture.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blueprint.architecture.myapplication.model.popularMovies.MovieItem
import com.blueprint.architecture.myapplication.repository.MovieRepository

/**
 * Created by Wajid Ali on 8/2/18.
 */
class MovieListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var movies: MutableLiveData<ArrayList<MovieItem?>?> = MutableLiveData()
    private var pageNumber = 0
    private val movieModel: MovieRepository = MovieRepository()

    fun getPopularMovies(){
        movieModel.getPopularMovies(pageNumber + 1).observeForever {
            movies.value = it?.results
            pageNumber = it?.page ?: 0
        }
    }

}