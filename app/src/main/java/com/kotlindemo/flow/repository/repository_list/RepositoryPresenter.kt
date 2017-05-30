package com.kotlindemo.flow.repository.repository_list

import client.yalantis.com.githubclient.api.GeneralErrorHandler
import com.kotlindemo.manager.ApiManager
import com.kotlindemo.mvp.BasePresenterImpl
import rx.functions.Action1

/**
 * Created by ankit on 30/5/17.
 */
class RepositoryPresenter : BasePresenterImpl<RepositoryContract.View>(),RepositoryContract.Presenter {


    override fun getRepos() {

        ApiManager.loadGitRepos("Yalantis","public")
                .doOnError { mView?.showError(it.toString()) }
                .subscribe(Action1 { mView?.showOrganization(it) },
                        GeneralErrorHandler(mView, true,{throwable, errorBody, isNetwork -> mView?.showError(throwable.localizedMessage) }))

    }
}