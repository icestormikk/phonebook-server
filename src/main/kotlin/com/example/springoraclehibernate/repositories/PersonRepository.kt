package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.Person
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
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

    @Query(
        value = "SELECT" +
                "    PERSON.ID, NAME, SURNAME, PATRONYMIC, EMAIL, ISQ_ID, AVATAR " +
                "FROM PERSON" +
                "    JOIN INFOBOOK I on PERSON.ID = I.PERSON_ID" +
                "    WHERE PHONENUMBER = :phoneNumber",
        nativeQuery = true
    )
    fun findPersonByPhone(@Param("phoneNumber") phone: String) : Optional<Person>
}