package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface PersonRepository : CrudRepository<Person, BigInteger> {
    fun findAllByName(name: String) : List<Person>
    fun findAllBySurname(surname: String) : List<Person>
    fun findAllByPatronymic(patronymic: String) : List<Person>
    fun findPersonByEmail(email: String) : Optional<Person>
    fun findPersonByIsqId(isqId: BigInteger) : Optional<Person>
}