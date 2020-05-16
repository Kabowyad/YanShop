package com.example.yanshop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.yanshop.data.BasketProductDaoImpl
import com.example.yanshop.data.BasketProductDao
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {
    @Provides
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    fun provideBasketProduct(preferences: SharedPreferences): BasketProductDao =
        BasketProductDaoImpl(preferences)
}