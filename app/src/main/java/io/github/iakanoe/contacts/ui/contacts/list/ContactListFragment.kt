package io.github.iakanoe.contacts.ui.contacts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.iakanoe.contacts.databinding.FragmentContactListBinding
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.ui.contacts.ContactViewModel
import io.github.iakanoe.contacts.ui.contacts.ContactsActivity
import io.github.iakanoe.contacts.util.setVisible
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ContactListFragment : Fragment(), ContactItemListener {

    private val contactViewModel: ContactViewModel by activityViewModels()

    private lateinit var favoritesAdapter: ContactListAdapter
    private lateinit var othersAdapter: ContactListAdapter

    private var binding: FragmentContactListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactViewModel.getData()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as ContactsActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        othersAdapter = ContactListAdapter(this)
        favoritesAdapter = ContactListAdapter(this)

        binding?.apply {
            favoritesList.apply {
                adapter = favoritesAdapter
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }

            othersList.apply {
                adapter = othersAdapter
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }

        lifecycleScope.launchWhenResumed {
            contactViewModel.getState().collect {
                when (it) {
                    is ContactViewModel.ContactListUiState.Loading -> {
                        applyLoadingState()
                    }
                    is ContactViewModel.ContactListUiState.Error -> {
                        applyErrorState(it.message)
                    }
                    is ContactViewModel.ContactListUiState.Success -> {
                        applySuccessState(it.model)
                    }
                }
            }
        }
    }

    private fun applyLoadingState() = binding?.apply {
        scrollView.setVisible(false)
        progressBar.setVisible(true)
    }

    private fun applyErrorState(message: String) = binding?.apply {
        scrollView.setVisible(false)
        progressBar.setVisible(false)
        showSnackbar(message)
    }

    private fun applySuccessState(model: ContactViewModel.UiModel) = binding?.apply {
        model.others.let {
            othersAdapter.submitList(it)
            othersGroup.setVisible(it.isEmpty().not())
        }

        model.favorites.let {
            favoritesAdapter.submitList(it)
            favoritesGroup.setVisible(it.isEmpty().not())
            othersGroup.setVisible(it.isEmpty().not())
        }

        scrollView.setVisible(true)
        progressBar.setVisible(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClick(contact: Contact) {
        contactViewModel.selected = contact
        findNavController().navigate(ContactListFragmentDirections.openContactDetails())
    }

    private fun showSnackbar(message: String) = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}