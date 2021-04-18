package com.example.desafiogitapp.data.repository

import com.example.desafiogitapp.data.model.RepositoriesResponse

interface MainRepository {
    suspend fun getRepositories(page: Int): RepositoriesResponse
}