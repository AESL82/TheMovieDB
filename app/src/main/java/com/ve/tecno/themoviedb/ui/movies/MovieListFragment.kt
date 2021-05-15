package com.ve.tecno.themoviedb.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.ve.tecno.themoviedb.R
import com.ve.tecno.themoviedb.common.Resource

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : Fragment() {

    private var columnCount = 2
    private lateinit var movieAdapter: MyMovieRecyclerViewAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var progressBarAnimation: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)

        progressBarAnimation = view.findViewById(R.id.progressbar_animation)
        movieAdapter = MyMovieRecyclerViewAdapter()

        // Set the adapter
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = movieAdapter
        }

        // Observer
        moviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    movieAdapter.setData(response.data?.results)
                    recyclerView.scheduleLayoutAnimation()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, "Error: ${response.message}", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }


        })

        return view
    }

    private fun hideProgressBar() {
        progressBarAnimation.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBarAnimation.visibility = View.VISIBLE
    }

}