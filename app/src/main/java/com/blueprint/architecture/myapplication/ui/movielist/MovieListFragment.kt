package com.blueprint.architecture.myapplication.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blueprint.architecture.myapplication.R
import com.blueprint.architecture.myapplication.model.popularMovies.MovieItem
import com.blueprint.architecture.myapplication.viewModel.MovieListViewModel

/**
 * Created by Wajid Ali
 */
class MovieListFragment : Fragment(), LifecycleOwner {
    private var mViewModel: MovieListViewModel? = null
    private val movieList: ArrayList<MovieItem?> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: MoviesAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movie_list_fragment, container, false)
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        mAdapter = MoviesAdapter(movieList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.adapter = mAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        mViewModel?.movies?.observe(MovieListFragment@this, Observer { movieModels ->
            movieList.addAll(movieModels ?: ArrayList())
            mAdapter?.notifyDataSetChanged()
        })
        mViewModel?.getPopularMovies()
    }

    companion object {
        @JvmStatic
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }
}