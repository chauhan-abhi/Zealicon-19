package com.jss.abhi.zealicon.di

import com.jss.abhi.zealicon.ZealApp
import com.jss.abhi.zealicon.fragments.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: ZealApp)
    fun inject(registerFragment: RegisterFragment)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule):Builder

        fun build(): AppComponent
    }
}