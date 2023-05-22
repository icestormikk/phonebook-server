package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.domain.dto.CityDTO
import com.example.springoraclehibernate.services.implementations.CityServiceImpl
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
 * Controller for processing requests for manipulating [City] entities
 * @property cityServiceImpl a set of methods for manipulating [City] entities
 */
@RestController
@RequestMapping("/cities")
@CrossOrigin
class CityController(
    @Autowired
    private val cityServiceImpl: CityServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [City] entities
     */
    @GetMapping
    fun getAllCities(@RequestParam(required = false) withTitles: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(cityServiceImpl.getAllCities(withTitles), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [City] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [City] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getOneCityById(@RequestParam value: String) : ResponseEntity<City> {
        val updatedId = try {
            value.toBigInteger()
        } catch (ex: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val city = cityServiceImpl.getCityById(updatedId)

        return if (city == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(city, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to get a [City] entity by its name
     * @param value the value of the title parameter
     * @return the [City] entity, if there is one, otherwise NOT_FOUND
     */
    @GetMapping("/title")
    fun getOneCityByTitle(@RequestParam value: String) : ResponseEntity<City> {
        val city = cityServiceImpl.getCityByTitle(value)

        return if (city == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(city, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [City] entity to the database
     * @param cityDTO basic information about the new [City] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addCity(@RequestBody cityDTO: CityDTO) : ResponseEntity<City> {
        return try {
            val savedEntity = cityServiceImpl.addCity(cityDTO)
            ResponseEntity(savedEntity, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Processing a request to delete an [City] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [City] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun removeCityById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (ex: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            cityServiceImpl.removeCityById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [City] entity data in the database
     * @param city basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateCategory(@RequestBody city: City) : ResponseEntity<City> {
        return try {
            val response = cityServiceImpl.updateCity(city)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}