package com.ui.innoguestapplication.backend

import com.google.gson.annotations.SerializedName
import com.ui.innoguestapplication.events.FaqElem
import com.ui.innoguestapplication.events.Event
import com.ui.innoguestapplication.events.MainEvent
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface API {
    @FormUrlEncoded
    @POST("/api/auth/")
    fun auth(@Field("login") login: String, @Field("password") password: String): Call<ResponseRest>

    @FormUrlEncoded
    @POST("/api/getdata/")
    fun getData(@Field("token") token: String): Call<ResponseRest>
}


data class ResponseRest(
        @SerializedName("error_request") val error_request: Int?,
        @SerializedName("body") val body: RespBody?
)

data class RespBody(
        @SerializedName("success") val success: Int?,
        @SerializedName("error") val error: Int?,
        @SerializedName("data") val data: RespData?
)

data class RespData(
        @SerializedName("token") val token: String?,
        @SerializedName("user") val user: RespUser?,
        @SerializedName("event") val event: MainEvent?,
        @SerializedName("schedule") val schedule: ArrayList<Event?>?,
        @SerializedName("faq") val faq: ArrayList<FaqElem?>?
)

data class RespUser(
        @SerializedName("id") val id: Int?,
        @SerializedName("email") val email: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("barcode") val barcode: String?
)

