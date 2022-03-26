package com.example.a18.data.retrofitrequest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

//    "userId": 1,
//    "id": 1,
//    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"

@Parcelize
data class RequestModel(
    @SerializedName("userId")
    val userId:String,
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("body")
    val body:String):Parcelable


@Parcelize
data class PostModel(
    @SerializedName("userId")
    val userId:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("body")
    val body:String):Parcelable