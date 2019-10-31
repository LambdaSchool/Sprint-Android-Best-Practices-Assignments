package com.stepasha.neewsfeedinjector.component

import com.stepasha.neewsfeedinjector.BaseView
import com.stepasha.neewsfeedinjector.module.ContextModule
import com.stepasha.neewsfeedinjector.module.NetworkModule
import com.stepasha.neewsfeedinjector.ui.PostPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjectorComponent {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjectorComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}