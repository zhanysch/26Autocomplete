package com.example.cinema.data.remote

import com.example.cinema.data.modelData.CinemaSearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbService {

    @GET(".")
    fun getCinema(@Query("apikey") apiKey: String , @Query("s")title:String):
            Call<CinemaSearchModel>


                    //http://www.omdbapi.com/?s=spider&apikey=90b2f7c2
}