package com.example.a18


import com.example.a18.di.AppComponent
import com.example.a18.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ProjectApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        DI.appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return DI.appComponent
    }
}
object DI {
    lateinit var appComponent: AppComponent
        internal set
}

