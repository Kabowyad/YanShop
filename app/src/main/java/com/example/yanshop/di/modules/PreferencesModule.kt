package com.example.yanshop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.yanshop.data.SharedPreferencesBasketDao
import com.example.yanshop.domain.BasketProductDao
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {
    @Provides
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    fun provideBasketProduct(preferences: SharedPreferences): BasketProductDao =
        SharedPreferencesBasketDao(preferences)
}