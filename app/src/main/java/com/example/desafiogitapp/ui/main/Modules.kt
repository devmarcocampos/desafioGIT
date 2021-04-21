package com.example.desafiogitapp.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.desafiogitapp.data.repository.MainRepository
import com.example.desafiogitapp.data.repository.MainRepositoryImpl
import com.example.desafiogitapp.source.remote.Api
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

val ctx = MyApp.applicationContext()
val cacheSize = (5 * 1024 * 1024).toLong()
val myCache = Cache( ctx.cacheDir, cacheSize)

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}


fun provideOfflineCacheInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        var request = chain.request()
        var cacheHeaderValue = if (!hasNetwork(context)!!){
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1
        } else {
            "public, max-age=" + 5
        }
        request = request.newBuilder().header("Cache-Control", cacheHeaderValue).build()
        chain.proceed(request)
    }
}

fun provideCacheInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        var cacheHeaderValue = if (!hasNetwork(context)!!){
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1
        } else {
            "public, max-age=" + 5
        }
        //request = request.newBuilder().build()
        val response = chain.proceed(request)
        response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheHeaderValue)
                .build()
    }
}

val retrofitModule = module {
    fun providesRetrofit(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor(provideOfflineCacheInterceptor(ctx))
                .addNetworkInterceptor(provideCacheInterceptor(ctx))
                .build()

        val BASE_URL = "https://api.github.com/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { providesRetrofit() }
}