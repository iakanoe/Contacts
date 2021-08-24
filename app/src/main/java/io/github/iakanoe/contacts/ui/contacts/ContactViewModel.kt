package io.github.iakanoe.contacts.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.usecase.GetContactListUseCase
import io.github.iakanoe.contacts.usecase.UpdateContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactListUseCase: GetContactListUseCase, private val updateContactUseCase: UpdateContactUseCase
) : ViewModel() {

    private val state: MutableStateFlow<ContactListUiState> = MutableStateFlow(ContactListUiState.Loading)
    lateinit var selected: Contact

    fun getState(): StateFlow<ContactListUiState> = state

    fun getData() = viewModelScope.launch {
        state.emit(ContactListUiState.Loading)

        try {
            state.emit(ContactListUiState.Success(parseData(getContactListUseCase())))
        } catch (e: Exception) {
            state.emit(ContactListUiState.Error(e.message ?: "Unknown error"))
            e.printStackTrace()
        }
    }

    fun updateSelected() {
        val value = getState().value as ContactListUiState.Success

        // Making a general list for it to be parsed again into groups
        val contacts = arrayListOf<Contact>()
        contacts.addAll(value.model.favorites)
        contacts.addAll(value.model.others)
        // The list should not be modified. Objects are always passed by reference,
        // so the selected contact should already be updated on one of the model's lists.

        viewModelScope.launch {
            state.emit(ContactListUiState.Success(parseData(contacts)))
            updateContactUseCase(selected)
        }
    }

    private fun parseData(contacts: List<Contact>) = UiModel(
        favorites = contacts.filter { c -> c.isFavorite }.sortedBy { c -> c.name },
        others = contacts.filter { c -> !c.isFavorite }.sortedBy { c -> c.name }
    )

    data class UiModel(
        val favorites: List<Contact>,
        val others: List<Contact>
    )

    sealed class ContactListUiState {
        data class Success(val model: UiModel) : ContactListUiState()
        data class Error(val message: String) : ContactListUiState()
        object Loading : ContactListUiState()
    }
}