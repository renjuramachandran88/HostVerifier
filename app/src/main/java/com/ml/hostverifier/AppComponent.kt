package com.ml.hostverifier

import android.content.Context
import com.ml.hostverifier.home.HomeActivity
import com.ml.hostverifier.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)

}
