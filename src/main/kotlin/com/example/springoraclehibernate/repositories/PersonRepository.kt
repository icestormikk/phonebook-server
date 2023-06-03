package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.address.interfaces.PersonWithTitles
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
        value = "SELECT\n" +
                "    PERSON.ID,\n" +
                "    AVATAR,\n" +
                "    EMAIL,\n" +
                "    ISQ_ID,\n" +
                "    NAME,\n" +
                "    SURNAME,\n" +
                "    PATRONYMIC,\n" +
                "    C2.TITLE AS Category,\n" +
                "    C4.TITLE || ', ' || C3.TITLE || ', ул.' || S2.TITLE || ', д.' || A2.HOUSENUMBER || nvl2(A2.FLATNUMBER, ', кв. ' || A2.FLATNUMBER, '') AS Address\n" +
                "FROM PERSON\n" +
                "    JOIN CATEGORY C2 on C2.ID = PERSON.CATEGORY_ID\n" +
                "    JOIN ADDRESS A2 on A2.ID = PERSON.ADDRESS_ID\n" +
                "    JOIN COUNTRY C3 on C3.ID = A2.COUNTRY_ID\n" +
                "    JOIN CITY C4 on C4.ID = A2.CITY_ID\n" +
                "    JOIN STREET S2 on S2.ID = A2.STREET_ID",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<PersonWithTitles>

    @Query(
        value = "SELECT" +
                "    PERSON.ID, " +
                "    NAME, " +
                "    SURNAME, " +
                "    PATRONYMIC, " +
                "    EMAIL, " +
                "    ISQ_ID, " +
                "    AVATAR, " +
                "    ADDRESS_ID, " +
                "    CATEGORY_ID " +
                "FROM PERSON" +
                "    JOIN INFOBOOK I on PERSON.ID = I.PERSON_ID" +
                "    WHERE PHONENUMBER = :phoneNumber",
        nativeQuery = true
    )
    fun findPersonByPhone(@Param("phoneNumber") phone: String) : Optional<Person>
}