package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.PhoneHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PhoneHistoryRepository : CrudRepository<PhoneHistory, Long> {
    fun findByPersonID(personID: Long) : Optional<PhoneHistory>
}