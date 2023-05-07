package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface PersonRepository : CrudRepository<Person, BigInteger> {
    fun findAllByName(name: String) : Optional<Person>
    fun findAllBySurname(surname: String) : Optional<Person>
    fun findAllByPatronymic(patronymic: String) : Optional<Person>
}