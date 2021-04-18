package com.example.desafiogitapp.source.remote

import com.example.desafiogitapp.data.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("page") page: String
    ): RepositoriesResponse
}