package com.jss.abhijeet.zealicon.di

import com.jss.abhijeet.zealicon.ZealApp
import com.jss.abhijeet.zealicon.fragments.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: ZealApp)
    fun inject(registerFragment: RegisterFragment)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}