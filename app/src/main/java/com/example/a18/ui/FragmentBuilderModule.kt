package com.example.a18.ui


import com.example.a18.ui.changefieldsfragment.ChangeFieldsFragment
import com.example.a18.ui.listfragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun bindChangeFieldsFragment(): ChangeFieldsFragment
}