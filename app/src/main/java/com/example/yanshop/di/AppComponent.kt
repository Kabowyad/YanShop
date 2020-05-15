package com.example.yanshop.di

import android.content.Context
import com.example.yanshop.di.modules.ApiModule
import com.example.yanshop.di.modules.PreferencesModule
import com.example.yanshop.ui.BasketActivity
import com.example.yanshop.ui.CatalogActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        ApiModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: CatalogActivity)
    fun inject(activity: BasketActivity)
}