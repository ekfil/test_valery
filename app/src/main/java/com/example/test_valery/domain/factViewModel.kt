package com.example.test_valery.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_valery.data.ApiService
import com.example.test_valery.data.Resp.Responce
import com.example.test_valery.data.ServiceInstance
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class factViewModel :ViewModel() {
    var responce : MutableLiveData<Responce>
      var sucsseded :  MutableLiveData<Boolean>

    init {
        responce = MutableLiveData<Responce>()
        sucsseded =MutableLiveData<Boolean>()
    }

    fun getService() {
            ServiceInstance.buildRetrofit().create(ApiService::class.java).getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())
    }

    fun getData():MutableLiveData<Responce>{
        return responce
    }

    private fun getObserver(): Observer<Responce> {
        return object :Observer<Responce>{
            override fun onSubscribe(d: Disposable) {
                sucsseded.postValue(false)
            }

            override fun onNext(t: Responce) {
                responce.postValue(t)
            }

            override fun onError(e: Throwable) {
  //              responce.postValue(null)
                Log.d("TAG", "onError: ${e.message}")
            }

            override fun onComplete() {
                sucsseded.postValue(true)
            }

        }

    }
    fun getSearch(input :CharSequence): Responce? {
        val filtred =responce.value
        filtred?.forEach {
            it._id.filter { it.toString().contains(input.toString().lowercase()) }
        }
        return filtred

    }



}