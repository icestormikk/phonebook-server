package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.dto.PersonDTO
import org.springframework.web.multipart.MultipartFile
import java.math.BigInteger

interface PersonService {
    fun getPeople() : List<Person>
    fun getPersonById(id: BigInteger) : Person?
    fun getPersonsByName(name: String) : List<Person>
    fun getPersonsBySurname(surname: String) : List<Person>
    fun getPersonsByPatronymic(patronymic: String) : List<Person>
    fun getPersonByEmail(email: String) : Person?
    fun getPersonByIsqId(isqId: BigInteger) : Person?
    fun addPerson(person: Person) : Person
    fun updatePerson(personDTO: PersonDTO) : Person
    fun setAvatar(file: MultipartFile, id: BigInteger): Person
    fun removePersonById(id: BigInteger)
}