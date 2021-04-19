package com.example.desafiogitapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafiogitapp.BaseViewModel
import com.example.desafiogitapp.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val mainRepository: MainRepository
) : BaseViewModel() {
    private val _states = MutableLiveData<MainViewState>()
    val states: LiveData<MainViewState>
        get() =_states

    fun getRepositories(page: Int) {
        launch {
            try {
                val response = mainRepository.getRepositories(page)
                _states.value = MainViewState.ShowRepositories(response.items)
            } catch (exception: Exception) {
                _states.value = MainViewState.ShowError("erro")
            }
        }
    }
}