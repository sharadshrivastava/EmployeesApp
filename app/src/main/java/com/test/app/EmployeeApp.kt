package com.test.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmployeeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: EmployeeApp? = null
        fun get(): EmployeeApp? = instance
    }
}