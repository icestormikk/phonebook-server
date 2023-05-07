package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface CountryRepository : CrudRepository<Country, BigInteger> {
    fun getCountryByTitle(title: String) : Optional<Country>
}
