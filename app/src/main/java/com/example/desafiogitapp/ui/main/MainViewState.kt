package com.example.desafiogitapp.ui.main

import com.example.desafiogitapp.data.model.Repository

sealed class MainViewState {
    data class ShowRepositories(val repositories: ArrayList<Repository>): MainViewState()
    data class ShowError(val error: String): MainViewState()
}