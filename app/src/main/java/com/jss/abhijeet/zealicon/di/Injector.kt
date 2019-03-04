package com.jss.abhijeet.zealicon.di

import com.jss.abhijeet.zealicon.ZealApp


object Injector {
    lateinit var appComponent: AppComponent

    fun init(app: ZealApp) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        appComponent.inject(app)
    }

}