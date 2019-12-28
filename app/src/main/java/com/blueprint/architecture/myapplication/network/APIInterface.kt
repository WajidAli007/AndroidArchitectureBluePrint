package com.blueprint.architecture.myapplication.network

import com.blueprint.architecture.myapplication.model.movieDetails.MovieDetails
import com.blueprint.architecture.myapplication.model.popularMovies.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Wajid Ali
 */
interface APIInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNumber : Int): Call<PopularMoviesResponse?>?

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId : Int) : Call<MovieDetails>
}