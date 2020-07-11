package com.ui.innoguestapplication.backend

import android.util.JsonToken
import android.util.Log
import com.ui.innoguestapplication.sqlite_database.LoginData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.NullPointerException

fun validData(resp: ResponseRest): APIRequests.DataState{
    return try {
        when(resp.error_request){
            1 -> APIRequests.DataState.NO_TOKEN
            else -> when(resp.body!!.error){
                1 -> APIRequests.DataState.WRONG_TOKEN
                2 -> APIRequests.DataState.USER_NOT_EXIST
                else -> APIRequests.DataState.NO_ERROR
            }
        }
    } catch (e: NullPointerException){
        APIRequests.DataState.ERROR
    }
}

fun validAuth(resp: ResponseRest): APIRequests.LoginState{
    return try {
        when (resp.error_request) {
            1 -> APIRequests.LoginState.ERROR
            else -> when (resp.body!!.success) {
                0 -> when (resp.body.error) {
                    1 -> APIRequests.LoginState.WRONG_LOGIN
                    else -> APIRequests.LoginState.WRONG_PASSWORD
                }
                else -> APIRequests.LoginState.NO_ERRORS
            }
        }
    } catch (e: NullPointerException){
        APIRequests.LoginState.ERROR
    }
}

object Backend {
    fun auth(loginData: LoginData, callback: Callback<ResponseRest>) = RestAPIService().auth(loginData, callback)

    fun getData(token: String, callback: Callback<ResponseRest>) = RestAPIService().getData(token, callback)


    private class RestAPIService() {
        val retrofit = RestBuilder.buildService(API::class.java)

        fun auth(request: LoginData, callback: Callback<ResponseRest>) {
            //retrofit = RestBuilder.buildService(API::class.java)
            retrofit.auth(request.email, request.password).enqueue(callback)
        }

        fun getData(token: String, callback: Callback<ResponseRest>) {
            retrofit.getData(token).enqueue(callback)
        }
    }

    private object RestBuilder {
        internal val logging = HttpLoggingInterceptor()
        private val client = OkHttpClient.Builder()

        fun setupLog() {
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        fun <T> buildService(service: Class<T>): T {
            setupLog()
            return Retrofit.Builder()
                    .baseUrl("https://floating-shore-42529.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build().create(service)

        }
    }
}



