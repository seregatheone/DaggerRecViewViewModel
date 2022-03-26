package com.example.a18.data.retrofitrequest

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject

interface RequestService {

    @GET("posts")
    suspend fun getRequest(): List<RequestModel>

    @POST("posts")
    suspend fun postRequest(@Body model : PostModel) : Response<RequestModel>
}
class RequestsHelper @Inject constructor(private val apiRequests: RequestService) {
    suspend fun getRequests() = apiRequests.getRequest()
    suspend fun postRequest(model : PostModel) :Response<RequestModel> =
        apiRequests.postRequest(model)
}
