package io.github.iakanoe.contacts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.iakanoe.contacts.domain.ContactRepository
import io.github.iakanoe.contacts.usecase.GetContactListUseCase
import io.github.iakanoe.contacts.usecase.UpdateContactUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetContactListUseCase(contactRepository: ContactRepository) = GetContactListUseCase(contactRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateContactUseCase(contactRepository: ContactRepository) = UpdateContactUseCase(contactRepository)
}