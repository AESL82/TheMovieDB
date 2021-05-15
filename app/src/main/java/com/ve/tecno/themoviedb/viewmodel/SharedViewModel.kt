package com.ve.tecno.themoviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ve.tecno.themoviedb.response.Person


class SharedViewModel: ViewModel() {

    var personSelected: MutableLiveData<Person> = MutableLiveData()

    fun selectPerson(p: Person) {
        personSelected.value = p
    }

}