package com.punyacile.networkingwithretrofit.network


import com.punyacile.networkingwithretrofit.model.ResponseUpdateNews
import com.punyacile.networkingwithretrofit.model.DataNews
import com.punyacile.networkingwithretrofit.model.ResponseAddNews
import com.punyacile.networkingwithretrofit.model.ResponseDataFilmItem
import com.punyacile.networkingwithretrofit.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @POST("news")
    fun postDataNews(@Body request: DataNews): Call<ResponseAddNews>

    @PUT("news/{id}")
    fun updateDataNews(
        @Path("id") id: Int,
        @Body request: DataNews
    ) : Call<List<ResponseUpdateNews>>


    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>
}