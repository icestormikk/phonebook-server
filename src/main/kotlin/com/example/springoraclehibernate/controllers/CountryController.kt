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

/**
 * Controller for processing requests for manipulating [Country] entities
 * @property countryServiceImpl a set of methods for manipulating [Country] entities
 */
@RestController
@RequestMapping("/countries")
@CrossOrigin
class CountryController(
    @Autowired
    private val countryServiceImpl: CountryServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [Country] entities
     */
    @GetMapping
    fun getAllCountries() : ResponseEntity<List<Country>> {
        val countries = countryServiceImpl.getAllCountries()
        return ResponseEntity(countries, HttpStatus.OK)
    }

    /**
     * Processing a request to get an [Country] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [Country] entity,
     * if there is one; otherwise NOT_FOUND
     */
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

    /**
     * Processing a request to get a [Country] entity by its name
     * @param value the value of the title parameter
     * @return the [Country] entity, if there is one, otherwise NOT_FOUND
     */
    @GetMapping("/title")
    fun getCountryByTitle(@RequestParam value: String) : ResponseEntity<Country> {
        val country = countryServiceImpl.getCountryByTitle(value)

        return if (country == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(country, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [Country] entity to the database
     * @param countryDTO basic information about the new [Country] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addCountry(@RequestBody countryDTO: CountryDTO) : ResponseEntity<Country> {
        val savedEntity = countryServiceImpl.addCountry(Country(title = countryDTO.title))
        return ResponseEntity(savedEntity, HttpStatus.OK)
    }

    /**
     * Processing a request to delete an [Country] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [Country] entity,
     * if there is one; otherwise NOT_FOUND
     */
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
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [Country] entity data in the database
     * @param country basic information to be applied to the entity
     * @return an entity with updated data
     */
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