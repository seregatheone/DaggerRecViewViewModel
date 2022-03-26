package com.example.a18.di

import android.app.Application
import com.example.a18.ProjectApplication
import com.example.a18.data.DataModule
import com.example.a18.data.reposirories.RetrofitRequestRepository
import com.example.a18.ui.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class, AndroidInjectionModule::class])]
interface AppComponent: AndroidInjector<ProjectApplication> {

    fun provideRetrofitRequestRepository(): RetrofitRequestRepository
    fun provideAppComponent(): AppComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
@Module(includes = [DataModule::class,UIModule::class])
class AppModule

@Scope
annotation class AppScope

