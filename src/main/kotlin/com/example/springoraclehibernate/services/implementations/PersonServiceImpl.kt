package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.math.BigInteger

@Service
class PersonServiceImpl(
    @Autowired
    private val personRepository: PersonRepository,
    @Autowired
    private val filesStorageServiceImpl: FilesStorageServiceImpl
) : PersonService {
    override fun getPeople(): List<Person> {
        return personRepository.findAll().toList()
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

    override fun updatePerson(person: Person): Person {
        if (person.id == null) {
            error("Id can not be null")
        }

        val personDB = personRepository
            .findById(person.id!!)
            .orElseThrow {
                error("The person with id ${person.id} does not exist in the database!")
            }
        person.avatar = personDB.avatar

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
