package com.example.desafiogitapp.data.repository

import com.example.desafiogitapp.data.model.RepositoriesResponse
import com.example.desafiogitapp.source.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private val api: Api
) : MainRepository {
    override suspend fun getRepositories(page: Int): RepositoriesResponse =
        withContext(Dispatchers.IO) {
            api.getRepositories("language:kotlin", "stars", page.toString())
        }
}