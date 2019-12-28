package com.blueprint.architecture.myapplication.model.popularMovies

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(

        @field:SerializedName("page")
        var page: Int? = null,

        @field:SerializedName("total_pages")
        var totalPages: Int? = null,

        @field:SerializedName("results")
        var results: ArrayList<MovieItem?>? = null,

        @field:SerializedName("total_results")
        var totalResults: Int? = null
)