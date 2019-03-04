package com.jss.abhijeet.zealicon

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.analytics.FirebaseAnalytics
import com.jss.abhijeet.zealicon.di.Injector

class ZealApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        //Stetho.initializeWithDefaults(this)
        FirebaseAnalytics.getInstance(this)
    }
}