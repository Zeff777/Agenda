package repository

import entidade.ContactEntidade


class ContactRepository {

    companion object{

        private val contactList = mutableListOf<ContactEntidade>()

        fun save(contact: ContactEntidade) {
            contactList.add(contact)
        }

        fun delete(contact: ContactEntidade) {

            var index = 0
            for (item in contactList.withIndex()) {
                if (item.value.name == contact.name && item.value.phone == contact.phone) {
                    index = item.index
                    break
                }
            }
            contactList.removeAt(index)
        }

        fun getList() : List<ContactEntidade> {
            return contactList
        }
    }

}