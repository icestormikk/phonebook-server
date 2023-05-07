package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.City
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface CityRepository : CrudRepository<City, BigInteger> {
    fun findCityByTitle(title: String) : Optional<City>
}