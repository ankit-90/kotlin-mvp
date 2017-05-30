package com.kotlindemo.mvp

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by ankit on 30/5/17.
 */
interface BaseView {

    fun getContext():Context

    fun showError(msg:String?)

    fun showError(@StringRes strResId:Int)

    fun showMessage(@StringRes strMsg:Int)

    fun showMessage(msg:String?)

}