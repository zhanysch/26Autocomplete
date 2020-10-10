package com.example.cinema.data.modelData

import com.google.gson.annotations.SerializedName

data class CinemaSearchModel (
    val totalResults: String,
    @SerializedName("Response")   // т.к в json передает переменки в Response, ->
    val response : String, //  а переменки необходим всегда писать маленк буквой, конвертим все с помощью SerilizedName
    @SerializedName("Search")
    val search : List<CinemaSearchItemModel>
)