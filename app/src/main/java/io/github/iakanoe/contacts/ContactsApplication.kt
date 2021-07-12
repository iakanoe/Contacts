package io.github.iakanoe.contacts

import android.app.Application
import io.github.iakanoe.contacts.data.ContactApiService
import io.github.iakanoe.contacts.data.ContactLocalService
import io.github.iakanoe.contacts.data.ContactRepositoryImpl
import io.github.iakanoe.contacts.domain.ContactRepository
import io.github.iakanoe.contacts.source.builder.DatabaseBuilder
import io.github.iakanoe.contacts.source.builder.RetrofitBuilder
import io.github.iakanoe.contacts.source.database.AppDatabase
import io.github.iakanoe.contacts.source.service.ContactApiServiceImpl
import io.github.iakanoe.contacts.source.service.ContactLocalServiceImpl
import io.github.iakanoe.contacts.ui.contacts.ContactViewModel
import io.github.iakanoe.contacts.usecase.GetContactListUseCase
import io.github.iakanoe.contacts.usecase.UpdateContactUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused") // until https://issuetracker.google.com/issues/74514347 is fixed... :(
class ContactsApplication : Application() {

    private val appModule = module {
        single { RetrofitBuilder.provideRetrofit() }
        single { DatabaseBuilder.provideAppDatabase(androidContext()) }

        factory { RetrofitBuilder.provideContactsAPI(get()) }
        factory { get<AppDatabase>().provideContactDao() }

        factory<ContactApiService> { ContactApiServiceImpl(get()) }
        factory<ContactLocalService> { ContactLocalServiceImpl(get()) }

        factory<ContactRepository> { ContactRepositoryImpl(get(), get()) }

        factory { GetContactListUseCase(get()) }
        factory { UpdateContactUseCase(get()) }

        viewModel { ContactViewModel(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ContactsApplication)
            modules(appModule)
        }
    }
}