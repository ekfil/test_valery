package com.example.test_valery.data

import com.example.test_valery.data.Resp.Responce
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {
    @GET("/facts")
    fun getFacts(): Observable<Responce>
}