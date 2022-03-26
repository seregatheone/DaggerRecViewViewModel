package com.example.a18.data.reposirories

import com.example.a18.data.retrofitrequest.PostModel
import com.example.a18.data.retrofitrequest.RequestModel
import com.example.a18.data.retrofitrequest.RequestsHelper
import retrofit2.Response
import javax.inject.Inject

class RetrofitRequestRepository @Inject constructor(private val requestsHelper: RequestsHelper) {
    suspend fun getUsers() = requestsHelper.getRequests()
    suspend fun postUser(model : PostModel):Response<RequestModel> = requestsHelper.postRequest(model)
}