package io.github.iakanoe.contacts.ui.contacts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.iakanoe.contacts.databinding.ItemContactListBinding
import io.github.iakanoe.contacts.domain.model.Contact

class ContactListAdapter(private val contactItemListener: ContactItemListener) :
    ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding, contactItemListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id
    }

    class ContactViewHolder(
        private val binding: ItemContactListBinding, private val contactItemListener: ContactItemListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.contact = contact
            binding.root.setOnClickListener { contactItemListener.onItemClick(contact) }

            // Preloading large image in cache to increase details page's speed.
            // Kinda bad practice but I don't have much time to build this feature properly rn.
            Glide.with(itemView.context).load(contact.largeImageURL).preload()
        }
    }
}

fun interface ContactItemListener {
    fun onItemClick(contact: Contact)
}