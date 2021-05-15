package com.ve.tecno.themoviedb.ui.people


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ve.tecno.themoviedb.common.Resource
import com.ve.tecno.themoviedb.repository.TheMovieDBRepository
import com.ve.tecno.themoviedb.response.PopularPeopleResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class PeopleViewModel @Inject constructor(
    private val theMovieDBRepository: TheMovieDBRepository
): ViewModel() {

    var popularPeople: MutableLiveData<Resource<PopularPeopleResponse>> = MutableLiveData()

    init {
        getPopularPeople()
        Log.i("MOVIES", "theMovieDBRepository en PeopleViewModel: $theMovieDBRepository")
    }

    fun getPopularPeople() = viewModelScope.launch {
        popularPeople.value = Resource.Loading()
        delay(1000)
        val response = theMovieDBRepository.getPopularPeople()
        popularPeople.value = handlePopularPeopleResponse(response)
    }

    private fun handlePopularPeopleResponse(response: Response<PopularPeopleResponse>) : Resource<PopularPeopleResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}