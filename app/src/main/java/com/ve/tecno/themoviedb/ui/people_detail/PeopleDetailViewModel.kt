package com.ve.tecno.themoviedb.ui.people_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ve.tecno.themoviedb.common.Resource
import com.ve.tecno.themoviedb.repository.TheMovieDBRepository
import com.ve.tecno.themoviedb.response.APIError
import com.ve.tecno.themoviedb.response.PersonDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class PeopleDetailViewModel @Inject constructor(private val theMovieDBRepository: TheMovieDBRepository): ViewModel() {

    var personInfoDetail: MutableLiveData<Resource<PersonDetail>> = MutableLiveData()

    init {
        Log.i("MOVIES", "theMovieDBRepository en PeopleViewModel: $theMovieDBRepository")
    }

    fun getPersonDetail(id: Int) = viewModelScope.launch {
        personInfoDetail.value = Resource.Loading()
        delay(1000)
        val response = theMovieDBRepository.getPersonDetail(id)
        personInfoDetail.value = handlePersonDetailResponse(response)
    }

    fun setSelectedPerson(idPerson: Int) {
        getPersonDetail(idPerson)
    }

    private fun handlePersonDetailResponse(response: Response<PersonDetail>) : Resource<PersonDetail> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        val error: APIError = theMovieDBRepository.parseError(response)
        return Resource.Error(error.status_message)
    }
}