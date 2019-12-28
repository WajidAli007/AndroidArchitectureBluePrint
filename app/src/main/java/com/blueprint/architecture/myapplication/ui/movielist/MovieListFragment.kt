package com.blueprint.architecture.myapplication.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blueprint.architecture.myapplication.MainActivity
import com.blueprint.architecture.myapplication.R
import com.blueprint.architecture.myapplication.base.BaseFragment
import com.blueprint.architecture.myapplication.model.popularMovies.MovieItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_fragment.*

/**
 * Created by Wajid Ali
 */
class MovieListFragment : BaseFragment(R.layout.movie_list_fragment), MovieInteractionCallback {

    companion object {
        fun newInstance() =
                MovieListFragment()
    }


    private var mViewModel: MovieListViewModel? = null
    private val movieList: ArrayList<MovieItem?> = ArrayList()
    private var mAdapter: MoviesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(ivLoadingPopularMovies)

        mAdapter = MoviesAdapter(movieList, this)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvMoviesListPopular?.layoutManager = mLayoutManager
        rvMoviesListPopular?.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        mViewModel?.movies?.observe(MovieListFragment@ this, Observer { movieModels ->
            if(movieModels == null){
                Toast.makeText(this.context, "Error Occurred", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            movieList.addAll(movieModels)
            if(movieList.isNotEmpty())
                ivLoadingPopularMovies.visibility = View.GONE
            mAdapter?.notifyDataSetChanged()
        })
        mViewModel?.getPopularMovies()
    }

    override fun onMovieClicked(movieId: Int) {
        if(activity is MainActivity){
            (activity as MainActivity).redirectToMovieDetails(movieId)
        }
    }
}