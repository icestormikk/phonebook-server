package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class PersonServiceImpl(
    @Autowired
    private val personRepository: PersonRepository
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

    override fun getPersonsByName(name: String): Person? {
        val person = personRepository.findAllByName(name)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun getPersonsBySurname(surname: String): Person? {
        val person = personRepository.findAllBySurname(surname)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun getPersonsByPatronymic(patronymic: String): Person? {
        val person = personRepository.findAllByPatronymic(patronymic)

        return if (person.isEmpty) {
            null
        } else {
            person.get()
        }
    }

    override fun addPerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun removePersonById(id: BigInteger) {
        val isPersonExist = personRepository.existsById(id)

        if (!isPersonExist) {
            error("The person with id $id does not exist in the database!")
        }
        personRepository.deleteById(id)
    }
}
