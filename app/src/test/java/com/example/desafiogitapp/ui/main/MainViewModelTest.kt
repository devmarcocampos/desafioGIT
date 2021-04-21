package com.example.desafiogitapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafiogitapp.data.model.Repository
import com.example.desafiogitapp.data.model.Owner
import com.example.desafiogitapp.data.model.RepositoriesResponse
import com.example.desafiogitapp.data.repository.MainRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class MainViewModelTest {

    lateinit var viewModel: MainViewModel
    lateinit var repository: MainRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mockk()
        viewModel = MainViewModel(repository)

        viewModel.coroutineContext = Dispatchers.Unconfined + SupervisorJob()
    }

    @Test
    fun `getRepositories - Verifica sucesso da requisicao`() = runBlocking {
        coEvery { repository.getRepositories(1) } returns getRepositoriesResponse()

        viewModel.getRepositories(1)

        Assert.assertEquals(viewModel.states.value, MainViewState.ShowRepositories(getRepositoriesResponse().repositories))
    }

    @Test
    fun `getRepositories - Verifica falha da requisicao`() = runBlocking {
        val exception: Exception = mockk()

        coEvery { repository.getRepositories(1) } throws exception

        viewModel.getRepositories(1)

        Assert.assertEquals(viewModel.states.value, MainViewState.ShowError(ERROR))
    }

    private fun getRepositoriesResponse() : RepositoriesResponse =
        RepositoriesResponse(arrayListOf(getItems()))

    private fun getItems() : Repository =
        Repository(1, "itemName", 1, 1, getOwner())

    private fun getOwner() : Owner =
        Owner(1, "loginOwnser", "avatarUrlOwner")

    companion object {
        const val ERROR = "erro"
    }
}