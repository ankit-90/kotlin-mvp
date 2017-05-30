package client.yalantis.com.githubclient.api

import android.support.annotation.StringRes
import android.text.TextUtils
import com.kotlindemo.R
import com.kotlindemo.mvp.BaseView
import retrofit2.adapter.rxjava.HttpException
import rx.functions.Action1
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by ankit on 30/5/17.
 */

class GeneralErrorHandler(view: BaseView? = null,
                          private val mShowError: Boolean = false,
                          val onFailure: (Throwable, ErrorBody?, Boolean) -> Unit)
: Action1<Throwable> {

    private val mViewReference: WeakReference<BaseView>

    init {
        mViewReference = WeakReference<BaseView>(view)
    }

    override fun call(throwable: Throwable) {
        var isNetwork = false
        var errorBody: ErrorBody? = null
        if (isNetworkError(throwable)) {
            isNetwork = true
            showMessage(R.string.internet_connection_unavailable)
        } else if (throwable is HttpException) {
            errorBody = ErrorBody.parseError(throwable.response())
            if (errorBody != null) {
                handleError(errorBody)
            }
        }

        onFailure(throwable, errorBody, isNetwork)
    }

    private fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }

    private fun handleError(errorBody: ErrorBody) {
        if (errorBody.code != ErrorBody.UNKNOWN_ERROR) {
            showErrorIfRequired(R.string.server_error)
        }
    }

    private fun showErrorIfRequired(@StringRes strResId: Int) {
        if (mShowError) {
            mViewReference.get()?.showError(strResId)
        }
    }

    private fun showErrorIfRequired(error: String) {
        if (mShowError && !TextUtils.isEmpty(error)) {
            mViewReference.get()?.showError(error)
        }
    }

    private fun showMessage(@StringRes strResId: Int) {
        mViewReference.get()?.showMessage(strResId)
    }

    private fun showMessage(error: String) {
        if (error.isNotEmpty()) {
            mViewReference.get()?.showError(error)
        }
    }

}