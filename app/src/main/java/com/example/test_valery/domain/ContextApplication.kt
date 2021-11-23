package com.example.test_valery.domain

import android.app.Application

class ContextApplication :Application() {
    companion object{
        lateinit var instance:ContextApplication
    }



    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}