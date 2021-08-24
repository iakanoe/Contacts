package io.github.iakanoe.contacts.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.iakanoe.contacts.data.ContactApiService
import io.github.iakanoe.contacts.data.ContactLocalService
import io.github.iakanoe.contacts.data.ContactRepositoryImpl
import io.github.iakanoe.contacts.domain.ContactRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindContactRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryImplModule {
        @Provides
        fun provideContactRepositoryImpl(apiService: ContactApiService, localService: ContactLocalService) =
            ContactRepositoryImpl(apiService, localService)
    }
}