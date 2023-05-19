package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.address.Country
import com.example.springoraclehibernate.domain.dto.CountryDTO
import com.example.springoraclehibernate.services.implementations.CountryServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
@CrossOrigin
class CountryController(
    @Autowired
    private val countryServiceImpl: CountryServiceImpl
) {
    @GetMapping
    fun getAllCountries() : ResponseEntity<List<Country>> {
        val countries = countryServiceImpl.getAllCountries()
        return ResponseEntity(countries, HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getCountryById(@RequestParam value: String) : ResponseEntity<Country> {
        val updatedId = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val country = countryServiceImpl.getCountryById(updatedId)
        return if (country == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(country, HttpStatus.OK)
        }
    }

    @GetMapping("/title")
    fun getCountryByTitle(@RequestParam value: String) : ResponseEntity<Country> {
        val country = countryServiceImpl.getCountryByTitle(value)

        return if (country == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(country, HttpStatus.OK)
        }
    }

    @PostMapping
    fun addCountry(@RequestBody countryDTO: CountryDTO) : ResponseEntity<Country> {
        val savedEntity = countryServiceImpl.addCountry(Country(title = countryDTO.title))
        return ResponseEntity(savedEntity, HttpStatus.OK)
    }

    @DeleteMapping
    fun removeCountryById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            countryServiceImpl.removeCountryById(updatedId)
            HttpStatus.OK
        } catch (_: NumberFormatException) {
            HttpStatus.BAD_REQUEST
        }
    }

    @PutMapping
    fun updateCountry(@RequestBody country: Country) : ResponseEntity<Country> {
        return try {
            val response = countryServiceImpl.updateCountry(country)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}