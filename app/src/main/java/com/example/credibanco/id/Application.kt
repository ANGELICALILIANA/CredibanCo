package com.example.credibanco.id

import android.app.Application
import com.example.credibanco.database.TransactionDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppComponent(): Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            TransactionDataBase.getInstance(this)
        } catch (e: Exception) {

        }
    }

}