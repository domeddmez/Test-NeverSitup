package com.example.test_neversitup.base

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_neversitup.api.Resource
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    var activity: Activity? = null

    private fun <T> subscribeData(mutableLiveData: MutableLiveData<Resource<T>>): Observer<Resource<T>?> =
        object :
            Observer<Resource<T>?> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onNext(respone: Resource<T>) {
                mutableLiveData.value = respone
            }
        }

    fun <T> serviceSuccess(response: Response<T>, mutableLiveData: MutableLiveData<Resource<T>>) {
        Observable.just(Resource.success(response.body() as T))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscribeData(mutableLiveData))
    }

    fun <T> serviceFail(
        response: Response<T>,
        message: String?,
        mutableLiveData: MutableLiveData<Resource<T>>
    ) {
        Observable.just(message?.let {
            Resource.error(response.code(),
                it,
                response.body() as T
            )
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscribeData(mutableLiveData))
    }

    fun <T> onFailure(t: Throwable, mutableLiveData: MutableLiveData<Resource<T>>, data: T?) {
        Observable.just(
            Resource.error(
                "ขออภัย ระบบขัดข้อง",
                data
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscribeData(mutableLiveData))
    }
}