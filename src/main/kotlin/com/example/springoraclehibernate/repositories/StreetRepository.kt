package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.Street
import com.example.springoraclehibernate.domain.address.interfaces.StreetWithTitlesType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface StreetRepository : CrudRepository<Street, BigInteger> {
    fun findStreetByTitle(title: String) : Optional<Street>
    @Query(
        value = "SELECT Street.title AS title, City.title AS cityTitle FROM City " +
                "JOIN Street ON Street.CITY_ID = City.id",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<StreetWithTitlesType>
}
