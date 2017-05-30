package com.kotlindemo.manager

import com.google.gson.GsonBuilder
import com.kotlindemo.api.GitHubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by zapbuild on 30/5/17.
 */
object ApiManager {

    private const val BASE_URL:String = "https://api.github.com/"

    private lateinit var mGitHubService:GitHubService

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }


    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level  = HttpLoggingInterceptor.Level.BODY
        }

        val client  = OkHttpClient.Builder().apply {
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .method(original.method(),original.body())
                        .build()
                chain.proceed(request)

            })
            addInterceptor(interceptor)
        }
        return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(createGsonConverterFactory())
                .build()
    }

    //configure gson
    private fun createGsonConverterFactory():GsonConverterFactory{
        val builder = GsonBuilder().serializeNulls().setPrettyPrinting()
        return GsonConverterFactory.create(builder.create())
    }

    private fun initServices(retrofit: Retrofit) {
        mGitHubService = retrofit.create(GitHubService::class.java)
    }



    fun loadGitRepos(name:String, reposType:String) =
            mGitHubService.getOrganization(name,reposType)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())!!



}