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
import com.ve.tecno.themoviedb.common.Constants
import com.ve.tecno.themoviedb.response.Movie
import kotlinx.android.synthetic.main.fragment_movie_list_item.view.*
/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var movies: List<Movie> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        holder.ivPhoto.load(Constants.IMAGE_BASE_URL + item.poster_path) {
            crossfade(true)
            placeholder(R.drawable.ic_bg_circle_empty)
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivPhoto: ImageView = mView.image_view_photo
        val tvTitle: TextView = mView.text_view_title
        val tvRating: TextView = mView.text_view_rating
    }
}
