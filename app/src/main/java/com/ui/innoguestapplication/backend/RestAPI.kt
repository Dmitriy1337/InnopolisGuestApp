package com.ui.innoguestapplication.backend

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface API {

    @FormUrlEncoded
    @POST("/api/auth/")
    fun auth(@Field("login") login: String, @Field("password") password: String): Call<ResponseRest>
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
        @SerializedName("user") val user: RespUser?
)

data class RespUser(
        @SerializedName("id") val id: Int?,
        @SerializedName("email") val email: String?,
        @SerializedName("tg") val tg: String?,
        @SerializedName("barcode") val barcode: String?
)

data class ReqBody(
        @SerializedName("login") val login: String?,
        @SerializedName("password") val password: String?
)