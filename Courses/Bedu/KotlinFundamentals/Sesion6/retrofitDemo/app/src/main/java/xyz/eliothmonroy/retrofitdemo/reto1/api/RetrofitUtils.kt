package xyz.eliothmonroy.retrofitdemo.reto1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(CONSTANTS.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getRetofitInstance():Retrofit{
        return retrofit
    }

}