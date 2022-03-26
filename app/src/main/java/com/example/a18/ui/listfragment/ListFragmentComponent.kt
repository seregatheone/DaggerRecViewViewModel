package com.example.a18.ui.listfragment


import androidx.lifecycle.ViewModel
import com.example.a18.DI
import com.example.a18.data.reposirories.RetrofitRequestRepository
import dagger.*
import dagger.multibindings.IntoMap
import javax.inject.Scope
import kotlin.reflect.KClass

@Component(modules = [ListFragmentModule::class])
@ListFragmentScope
interface ListFragmentComponent {

    fun viewModelFactory():MyViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun retrofitRequestRepository(retrofitRequestRepository: RetrofitRequestRepository):Builder

        fun build(): ListFragmentComponent
    }

    companion object {
        fun create() = with (DI.appComponent) {
            DaggerListFragmentComponent.builder()
                .retrofitRequestRepository(provideRetrofitRequestRepository())
                .build()
        }
    }
}
@Module
abstract class ListFragmentModule{

    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    abstract fun myViewModel(viewModel: MyViewModel):ViewModel


}

@Scope
annotation class ListFragmentScope

@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)