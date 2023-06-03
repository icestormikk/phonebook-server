package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.dto.PersonDTO
import com.example.springoraclehibernate.repositories.AddressRepository
import com.example.springoraclehibernate.repositories.CategoryRepository
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.services.PersonService
import com.example.springoraclehibernate.services.getFromRepositoryById
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.math.BigInteger

@Service
class PersonServiceImpl(
    @Autowired
    private val personRepository: PersonRepository,
    @Autowired
    private val filesStorageServiceImpl: FilesStorageServiceImpl,
    @Autowired
    private val categoryRepository: CategoryRepository,
    @Autowired
    private val addressRepository: AddressRepository
) : PersonService {
    override fun getPeople(withTitles: Boolean): List<Any> {
        val iterable = if (withTitles) {
            personRepository.findAllWithTitles()
        } else {
            personRepository.findAll()
        }

        return iterable.toList()
    }

    override fun getPersonById(id: BigInteger): Person? {
        val person = personRepository.findById(id)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun getPersonsByName(name: String): List<Person> {
        return personRepository.findAllByName(name)
    }

    override fun getPersonByPhone(phone: String): Person? {
        println(phone)
        val person = personRepository.findPersonByPhone(phone)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun getPersonsBySurname(surname: String): List<Person> {
        return personRepository.findAllBySurname(surname)
    }

    override fun getPersonsByPatronymic(patronymic: String): List<Person> {
        return personRepository.findAllByPatronymic(patronymic)
    }

    override fun getPersonByEmail(email: String): Person? {
        val person = personRepository.findPersonByEmail(email)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun getPersonByIsqId(isqId: BigInteger): Person? {
        val person = personRepository.findPersonByIsqId(isqId)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun addPerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun updatePerson(personDTO: PersonDTO): Person {
        if (personDTO.id == null) {
            error("Id can not be null")
        }

        val person = getFromRepositoryById(personDTO.id!!, personRepository, "person")
        val category = getFromRepositoryById(personDTO.categoryID!!, categoryRepository, "category")
        val address = getFromRepositoryById(personDTO.addressID!!, addressRepository, "address")
        person.name = personDTO.name
        person.surname = personDTO.surname
        person.patronymic = personDTO.patronymic
        person.email = personDTO.email
        person.isqId = personDTO.isqId
        person.addressID = address.id
        person.categoryID = category.id

        return personRepository.save(person)
    }

    override fun setAvatar(file: MultipartFile, id: BigInteger) : Person {
        val person = personRepository.findById(id).orElseThrow {
            error("The person with id $id does not exist in the database!")
        }
        val filename = filesStorageServiceImpl.save(file, "${person.id}")
        person.avatar = filename

        return personRepository.save(person)
    }

    override fun removePersonById(id: BigInteger) {
        val person = personRepository.findById(id)
        if (person.isEmpty) {
            error("The person with id $id does not exist in the database!")
        }

        val avatar = person.get().avatar
        if (avatar != null) {
            filesStorageServiceImpl.delete(avatar)
        }

        personRepository.deleteById(id)
    }
}
