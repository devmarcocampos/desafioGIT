package com.example.desafiogitapp.ui.main

import com.example.desafiogitapp.data.model.Items

sealed class MainViewState {
    data class ShowRepositories(val repositories: ArrayList<Items>): MainViewState()
    data class ShowError(val error: String): MainViewState()
}