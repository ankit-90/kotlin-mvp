package com.kotlindemo.mvp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kotlindemo.R

abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {


    protected abstract var mPresenter: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mPresenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    override fun showError(msg: String?) {
        Toast.makeText(this, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(strResId: Int) {
        Toast.makeText(this, strResId, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(strMsg: Int) {
        Toast.makeText(this, strMsg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(msg: String?) {
        Toast.makeText(this, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dettachView()
    }


}
