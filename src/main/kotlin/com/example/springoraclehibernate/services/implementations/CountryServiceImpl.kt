package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.Country
import com.example.springoraclehibernate.repositories.CountryRepository
import com.example.springoraclehibernate.services.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class CountryServiceImpl(
    @Autowired
    private val countryRepository: CountryRepository
) : CountryService {
    override fun getAllCountries(): List<Country> {
        return countryRepository.findAll().toList()
    }

    override fun getCountryById(id: BigInteger): Country? {
        val country = countryRepository.findById(id)

        return if (country.isEmpty) {
            null
        } else {
            country.get()
        }
    }

    override fun getCountryByTitle(title: String): Country? {
        val country = countryRepository.getCountryByTitle(title)

        return if (country.isEmpty) {
            null
        } else {
            country.get()
        }
    }

    override fun addCountry(country: Country): Country {
        return countryRepository.save(country)
    }

    override fun updateCountry(country: Country): Country {
        if (country.id == null) {
            error("Id can not be null")
        }

        if (!countryRepository.existsById(country.id!!)) {
            error("The country with id ${country.id} does not exist in the database!")
        }

        return countryRepository.save(country)
    }

    override fun removeCountryById(id: BigInteger) {
        val isCountryExist = countryRepository.existsById(id)

        if (!isCountryExist) {
            error("The country with id $id does not exist in the database!")
        }
        countryRepository.deleteById(id)
    }
}