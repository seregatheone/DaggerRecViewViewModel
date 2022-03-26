package com.example.a18.ui.listfragment

import android.util.Log
import androidx.lifecycle.*
import com.example.a18.Resource
import com.example.a18.data.reposirories.RetrofitRequestRepository
import com.example.a18.data.retrofitrequest.PostModel
import com.example.a18.data.retrofitrequest.RequestModel
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

class MyViewModel @Inject constructor(private val mainRepository: RetrofitRequestRepository) :
    ViewModel() {
    val myResponse = MutableLiveData<Response<RequestModel>>()
    fun getRequest() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun postRequest(model: PostModel) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                launch {
                    val response = mainRepository.postUser(model)
                    myResponse.postValue(response)
                    Log.i("mainRepository", response.body().toString())
                }
            } catch (e: Exception) {
                Log.e("Error in viewModelScope", e.toString())
            }
        }
    }
}

class MyViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("ViewModel $modelClass not found")
        return viewModelProvider.get() as T
    }
}
