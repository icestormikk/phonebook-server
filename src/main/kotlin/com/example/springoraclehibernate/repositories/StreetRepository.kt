package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.Street
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface StreetRepository : CrudRepository<Street, BigInteger> {
    fun findStreetByTitle(title: String) : Optional<Street>
}
