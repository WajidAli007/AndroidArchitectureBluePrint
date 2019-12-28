package com.blueprint.architecture.myapplication.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blueprint.architecture.myapplication.R
import com.blueprint.architecture.myapplication.model.popularMovies.MovieItem
import com.blueprint.architecture.myapplication.network.ApiClient
import com.blueprint.architecture.myapplication.ui.movielist.MoviesAdapter.MyViewHolder
import com.bumptech.glide.Glide

/**
 * Created by Wajid Ali
 */
class MoviesAdapter(private val moviesList: List<MovieItem?>,
                    private val callback : MovieInteractionCallback?) : RecyclerView.Adapter<MyViewHolder>() {

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        var name: TextView = view.findViewById<View>(R.id.name) as TextView
        var tvDesc: TextView = view.findViewById<View>(R.id.tvDescription) as TextView
        var ivMovieCover: ImageView = view.findViewById<View>(R.id.ivMovieCover) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.name.text = movie?.title
        holder.tvDesc.text = movie?.overview

        Glide.with(holder.ivMovieCover.context)
                .load(ApiClient.IMAGES_BASE_PATH + movie?.posterPath)
                .circleCrop()
                .placeholder(R.drawable.loading)
                .into(holder.ivMovieCover)

        holder.itemView.setOnClickListener {
            if (movie?.id != null) {
                callback?.onMovieClicked(movie.id.toInt())
            }
        }

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}