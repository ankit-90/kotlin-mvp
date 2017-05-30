package com.kotlindemo.mvp

/**
 * Created by ankit on 30/5/17.
 */
interface BasePresenter<in V: BaseView> {

    fun attachView(view:V)

    fun dettachView()

}