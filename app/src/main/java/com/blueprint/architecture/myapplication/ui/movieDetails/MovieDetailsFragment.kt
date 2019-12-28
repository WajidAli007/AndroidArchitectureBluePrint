package com.blueprint.architecture.myapplication.ui.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blueprint.architecture.myapplication.R
import com.blueprint.architecture.myapplication.base.BaseFragment

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }




}
