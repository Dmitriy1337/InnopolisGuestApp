package com.ui.innoguestapplication.backend

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object Backend {
    fun auth(login: String, password: String, callback: Callback<ResponseRest>){
        Log.d("Back", "auth")

        RestAPIService().auth(ReqBody(login, password), callback)
    }

}


private class RestAPIService {
    fun auth(request: ReqBody, callback: Callback<ResponseRest>) {
        val retrofit = RestBuilder.buildService(API::class.java)
        retrofit.auth(request.login!!, request.password!!).enqueue(callback)
    }

}


private object RestBuilder {
    internal val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()

    fun setupLog(){
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

