package com.example.desafiogitapp.ui.main

import com.example.desafiogitapp.data.repository.MainRepository
import com.example.desafiogitapp.data.repository.MainRepositoryImpl
import com.example.desafiogitapp.source.remote.Api
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val mainRepositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}

val apiModule = module {
    fun providesApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    single { providesApi(get()) }
}

val retrofitModule = module {
    fun providesRetrofit(): Retrofit {
        val BASE_URL = "https://api.github.com/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { providesRetrofit() }
}