package com.example.desafiogitapp.ui.main

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(retrofitModule, apiModule, mainRepositoryModule, mainViewModelModule))
        }
    }

//    fun gContext(): MyApp? =
//            instance
}