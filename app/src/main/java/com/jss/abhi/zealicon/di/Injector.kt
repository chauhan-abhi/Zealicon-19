package com.jss.abhi.zealicon.di

import com.jss.abhi.zealicon.ZealApp


object Injector {
    lateinit var appComponent: AppComponent

    fun init(app: ZealApp) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        appComponent.inject(app)
    }

}