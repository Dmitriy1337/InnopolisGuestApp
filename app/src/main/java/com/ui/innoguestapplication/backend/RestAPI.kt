package com.ui.innoguestapplication.backend

import com.google.gson.annotations.SerializedName
import com.ui.innoguestapplication.Event
import com.ui.innoguestapplication.MainEvent
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
        @SerializedName("schedule") val schedule: ArrayList<Event?>?
)

data class RespUser(
        @SerializedName("id") val id: Int?,
        @SerializedName("email") val email: String?,
        @SerializedName("tg") val tg: String?,
        @SerializedName("barcode") val barcode: String?
)
data class RespEvent(
        @SerializedName("title") val title: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("faq") val faq: String?,
        @SerializedName("groups_amount") val groups_amount: Int?,
        @SerializedName("start_date") val start_date: String?,
        @SerializedName("end_date") val end_date: String?
)
data class RespSchedElem(
        @SerializedName("title") val title: String?,
        @SerializedName("date_time") val date_time: String?,
        @SerializedName("start_date") val start_date: String?,
        @SerializedName("end_date") val end_date: String?,
        @SerializedName("location") val location: String?,
        @SerializedName("must_visit") val must_visit: Boolean?,
        @SerializedName("group_id") val group_id: String?,
        @SerializedName("lang") val lang: String?
)

