package com.kotlindemo.api

import client.yalantis.com.githubclient.model.Repository
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by ankit on 30/5/17.
 */
interface GitHubService {

    @GET("orgs/organization/repos")
    fun getOrganization(@Query("organization") name:String,
                        @Query("type") type:String
    ) : Observable<MutableList<Repository>>

}