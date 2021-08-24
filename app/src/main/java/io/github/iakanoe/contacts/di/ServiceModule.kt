package io.github.iakanoe.contacts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.iakanoe.contacts.data.ContactApiService
import io.github.iakanoe.contacts.data.ContactLocalService
import io.github.iakanoe.contacts.source.service.ContactApiServiceImpl
import io.github.iakanoe.contacts.source.service.ContactLocalServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindContactApiService(contactApiServiceImpl: ContactApiServiceImpl): ContactApiService

    @Binds
    abstract fun bindContactLocalService(contactLocalServiceImpl: ContactLocalServiceImpl): ContactLocalService
}