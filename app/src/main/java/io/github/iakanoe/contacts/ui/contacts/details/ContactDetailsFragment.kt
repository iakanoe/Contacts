package io.github.iakanoe.contacts.ui.contacts.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.iakanoe.contacts.R
import io.github.iakanoe.contacts.databinding.FragmentContactDetailsBinding
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.ui.contacts.ContactViewModel
import io.github.iakanoe.contacts.ui.contacts.ContactsActivity

@AndroidEntryPoint
class ContactDetailsFragment : Fragment() {

    private val contactViewModel: ContactViewModel by activityViewModels()

    private var binding: FragmentContactDetailsBinding? = null

    private lateinit var contact: Contact
    private lateinit var menu: Menu

    override fun onResume() {
        super.onResume()
        (requireActivity() as ContactsActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact = contactViewModel.selected
        binding!!.contact = contact
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        inflater.inflate(R.menu.menu_contact_details, menu)
        updateMenu()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_favorite -> true.also {
            contact.isFavorite = contact.isFavorite.not()
            updateMenu()
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun updateMenu() = menu.findItem(R.id.action_favorite).apply {
        if (contact.isFavorite) {
            title = resources.getString(R.string.action_unfavorite)
            setIcon(R.drawable.ic_favorite_true)
        } else {
            title = resources.getString(R.string.action_favorite)
            setIcon(R.drawable.ic_favorite_false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        contactViewModel.updateSelected()
        binding = null
    }
}