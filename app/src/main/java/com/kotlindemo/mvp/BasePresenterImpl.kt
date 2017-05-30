package com.kotlindemo.mvp

/**
 * Created by ankit on 30/5/17.
 */
open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun dettachView() {
        mView = null
    }
}