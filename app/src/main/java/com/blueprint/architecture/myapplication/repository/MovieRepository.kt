package com.blueprint.architecture.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.blueprint.architecture.myapplication.model.movieDetails.MovieDetails
import com.blueprint.architecture.myapplication.model.popularMovies.PopularMoviesResponse
import com.blueprint.architecture.myapplication.network.APIInterface
import com.blueprint.architecture.myapplication.network.ApiClient.clientAuthentication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Wajid Ali on 8/2/18.
 */
class MovieRepository {
    private var apiInterface: APIInterface? = null

    fun getPopularMovies(pageNumber: Int): MutableLiveData<PopularMoviesResponse?> {
        val popularMoviesLiveData = MutableLiveData<PopularMoviesResponse?>()
        apiInterface = clientAuthentication?.create(APIInterface::class.java)
        val call = apiInterface?.getPopularMovies(pageNumber)
        call!!.enqueue(object : Callback<PopularMoviesResponse?> {
            override fun onResponse(call: Call<PopularMoviesResponse?>, response: Response<PopularMoviesResponse?>) {
                if (response.body() != null) {
                    popularMoviesLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse?>, t: Throwable) {
                t.printStackTrace()
                popularMoviesLiveData.value = null
            }
        })
        return popularMoviesLiveData
    }

    fun getMovieDetails(movieId: Int) : MutableLiveData<MovieDetails?>{
        val movieDetailsLiveData = MutableLiveData<MovieDetails?>()
        apiInterface = clientAuthentication?.create(APIInterface::class.java)
        val call = apiInterface?.getMovieDetails(movieId)
        call!!.enqueue(object : Callback<MovieDetails?> {
            override fun onResponse(call: Call<MovieDetails?>, response: Response<MovieDetails?>) {
                if (response.body() != null) {
                    movieDetailsLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<MovieDetails?>, t: Throwable) {
                t.printStackTrace()
                movieDetailsLiveData.value = null
            }
        })
        return movieDetailsLiveData
    }

}