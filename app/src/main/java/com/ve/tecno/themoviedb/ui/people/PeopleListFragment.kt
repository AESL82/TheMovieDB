package com.ve.tecno.themoviedb.ui.people

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.ve.tecno.themoviedb.R
import com.ve.tecno.themoviedb.common.MyApp
import com.ve.tecno.themoviedb.common.Resource
import com.ve.tecno.themoviedb.viewmodel.SharedViewModel
import javax.inject.Inject

class PeopleListFragment : Fragment() {

    lateinit var sharedViewModel: SharedViewModel
    @Inject
    lateinit var peopleViewModel: PeopleViewModel

    private var columnCount = 2
    private lateinit var paginationProgressBar: LottieAnimationView
    private lateinit var peopleAdapter: PeopleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.applicationContext as MyApp).appComponent.inject(this)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_people_list, container, false)

        paginationProgressBar = view.findViewById(R.id.paginationProgressBar)

        peopleAdapter = PeopleRecyclerViewAdapter(sharedViewModel)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list)

        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = peopleAdapter
        }

        peopleViewModel.popularPeople.observe(viewLifecycleOwner, Observer { response ->

            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.results?.let { peopleResults ->
                        peopleAdapter.setData(peopleResults)
                        recyclerView.scheduleLayoutAnimation()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("Error", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        return view
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

}
