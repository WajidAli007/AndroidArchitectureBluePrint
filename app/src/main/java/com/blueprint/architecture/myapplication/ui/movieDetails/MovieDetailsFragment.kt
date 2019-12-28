package com.blueprint.architecture.myapplication.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blueprint.architecture.myapplication.R
import com.blueprint.architecture.myapplication.base.BaseFragment
import com.blueprint.architecture.myapplication.model.movieDetails.MovieDetails
import com.blueprint.architecture.myapplication.network.ApiClient
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    companion object {
        private const val MOVIE_ID_BUNDLE_KEY = "MOVIE_ID_BUNDLE_KEY"
        fun newInstance(movieId: Int) =
                MovieDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(MOVIE_ID_BUNDLE_KEY, movieId)
                    }
                }
    }

    var viewModelMovieDetails : MovieDetailsViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(ivLoadingPopularMovies)

        val movieId = arguments?.getInt(MOVIE_ID_BUNDLE_KEY, -1) ?: -1
        viewModelMovieDetails = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModelMovieDetails?.movies?.observe(MovieDetailsFragment@this, Observer {
            if(it != null){
                populateData(it)
            }else{
                showError("Details couldn't be retrieved, try again")
            }
        })
        viewModelMovieDetails?.getPopularMovies(movieId)

    }

    private fun populateData(details : MovieDetails){
        Glide.with(this)
                .load(ApiClient.IMAGES_BASE_PATH + details.posterPath)
                .fitCenter()
                .into(ivPosterMovieDetails)

        genres.tags = details.genres?.map {
            it.name ?: ""
        }
    }

    private fun showError(errorMsg : String){
        //TODO: IMPLEMENT THIS
    }


}
