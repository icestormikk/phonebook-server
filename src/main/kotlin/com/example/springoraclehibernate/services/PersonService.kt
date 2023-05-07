package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.Person
import java.math.BigInteger

interface PersonService {
    fun getPeople() : List<Person>
    fun getPersonById(id: BigInteger) : Person?
    fun getPersonsByName(name: String) : Person?
    fun getPersonsBySurname(surname: String) : Person?
    fun getPersonsByPatronymic(patronymic: String) : Person?
    fun addPerson(person: Person) : Person
    fun removePersonById(id: BigInteger)
}