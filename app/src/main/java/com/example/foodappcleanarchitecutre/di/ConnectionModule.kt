package com.example.foodappcleanarchitecutre.di

import android.content.Context
import com.codingwithmitch.food2forkcompose.presentation.util.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConnectionModule {
    @Provides
    fun provideConnectionManager(@ApplicationContext appContext: Context) = ConnectionLiveData.create(appContext)
}