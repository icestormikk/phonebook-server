package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.PhoneHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface PhoneHistoryRepository : CrudRepository<PhoneHistory, Long> {
    fun findByPersonID(personID: Long) : Optional<PhoneHistory>
    @Query(
        value = "SELECT ID FROM PERSON WHERE ID IN (" +
                "    SELECT PERSON_ID FROM PHONEHISTORY WHERE PHONE = :oldPhone" +
                ")",
        nativeQuery = true
    )
    fun findPeopleByOldPhone(@Param("oldPhone") oldPhone: String) : List<BigInteger>
}