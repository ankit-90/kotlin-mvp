package com.kotlindemo.flow.repository.repository_list

import client.yalantis.com.githubclient.model.Repository
import com.kotlindemo.mvp.BasePresenter
import com.kotlindemo.mvp.BaseView

/**
 * Created by zapbuild on 30/5/17.
 */
object RepositoryContract {

    interface View : BaseView {
        fun showOrganization(repos: MutableList<Repository>)
    }


    interface Presenter : BasePresenter<View> {

        fun getRepos()

    }

}