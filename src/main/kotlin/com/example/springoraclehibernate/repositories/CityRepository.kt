package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.domain.address.interfaces.CityWithTitlesType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface CityRepository : CrudRepository<City, BigInteger> {
    fun findCityByTitle(title: String) : Optional<City>
    @Query(
        value = "SELECT City.ID AS ID, City.title AS title, Country.title AS countryTitle FROM City " +
                "JOIN Country ON City.COUNTRY_ID = Country.id",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<CityWithTitlesType>
}