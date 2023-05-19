package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.Country
import java.math.BigInteger

interface CountryService {
    fun getAllCountries() : List<Country>
    fun getCountryById(id: BigInteger) : Country?
    fun getCountryByTitle(title: String) : Country?
    fun addCountry(city: Country) : Country
    fun updateCountry(country: Country) : Country
    fun removeCountryById(id: BigInteger)
}