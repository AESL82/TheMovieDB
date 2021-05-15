package com.ve.tecno.themoviedb.di

import com.ve.tecno.themoviedb.api.NetworkModule
import com.ve.tecno.themoviedb.ui.movies.MovieListFragment
import com.ve.tecno.themoviedb.ui.people.PeopleListFragment
import com.ve.tecno.themoviedb.ui.people_detail.PersonDetailScrollingActivity
import dagger.Component

@Component(modules = [NetworkModule::class])

interface ApplicationComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(peopleListFragment: PeopleListFragment)
    fun inject(personDetailScrollingActivity: PersonDetailScrollingActivity)
}
