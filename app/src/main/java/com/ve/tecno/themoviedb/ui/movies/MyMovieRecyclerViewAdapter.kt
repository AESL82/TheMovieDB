package com.ve.tecno.themoviedb.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.ve.tecno.themoviedb.R
import com.ve.tecno.themoviedb.common.Constantes
import com.ve.tecno.themoviedb.response.Movie

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieRecyclerViewAdapter() : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        holder.ivPhoto.load(Constantes.IMAGE_BASE_URL + item.poster_path) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount(): Int = values.size

    fun setData(newMovies: List<Movie>?) {
        values = newMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.text_view_title)
        val tvRating: TextView = view.findViewById(R.id.text_view_rating)
        val ivPhoto: ImageView = view.findViewById(R.id.image_view_photo)
    }
}