package com.example.desafiogitapp.ui.main

import com.example.desafiogitapp.data.model.Repository

interface OnRepositoryClickListener {
    fun onRepositoryClicked(repository: Repository)
}