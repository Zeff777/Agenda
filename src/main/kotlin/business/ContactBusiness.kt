package business
import java.lang.Exception
import repository.ContactRepository
import entidade.ContactEntidade

class ContactBusiness {

    private fun validate(name: String, phone: String) {
        if (name ==""){
            throw Exception("O nome é obrigatório!")
        }

        if (phone == "") {
            throw Exception("O telefone é obrigatório!")
        }
    }

    private fun validateDelete(name: String, phone: String) {
        if (name == "" || phone == "") {
            throw Exception("É necessário selecionar um contato antes de remover.")
        }
    }

    fun getContCountDescription() : String {
        val list = getList()
         return when {
            list.isEmpty() -> "0 Contatos"
            list.size == 1 -> "1 Contato"
            else -> "${list.size} contatos"
        }
    }

    fun save(name: String, phone: String) {

        validate(name, phone)
        val contato = ContactEntidade(name, phone)
        ContactRepository.save(contato)
    }

    fun delete(name: String, phone: String) {

        validateDelete(name, phone)
        val contato = ContactEntidade(name, phone)
        ContactRepository.delete(contato)
    }

    fun getList() : List<ContactEntidade> {
        return ContactRepository.getList()
    }

}