package com.andreisingeleytsev.listeningmeditationapp.di

import android.content.Context
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.DataStoreHub
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.repository.CategoryMeditationRepositoryImpl
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.OnboardStateRepository
import com.andreisingeleytsev.listeningmeditationapp.data.datastore.repository.OnBoardStateRepositoryImpl
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.CategoryMeditationRepository
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.MediaPlayerManager
import com.andreisingeleytsev.listeningmeditationapp.domain.repository.implementation.MediaPlayerManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideDataStoreHub(
        @ApplicationContext context: Context
    ) = DataStoreHub(context = context)

    @Provides
    @Singleton
    fun provideOnBoardStateRepository(dataStoreHub: DataStoreHub): OnboardStateRepository {
        return OnBoardStateRepositoryImpl(dataStoreHub)
    }

    @Provides
    @Singleton
    fun provideCategoryMeditationRepository(dataStoreHub: DataStoreHub): CategoryMeditationRepository{
        return CategoryMeditationRepositoryImpl(dataStoreHub)
    }

    @Provides
    @Singleton
    fun providesMediaPlayerManager(@ApplicationContext context: Context): MediaPlayerManager{
        return MediaPlayerManagerImpl(context)
    }
}