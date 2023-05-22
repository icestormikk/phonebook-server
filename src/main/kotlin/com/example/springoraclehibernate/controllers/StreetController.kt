package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.address.Street
import com.example.springoraclehibernate.domain.dto.StreetDTO
import com.example.springoraclehibernate.services.implementations.StreetServiceImpl
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
 * Controller for processing requests for manipulating [Street] entities
 * @property streetServiceImpl a set of methods for manipulating [Street] entities
 */
@RestController
@RequestMapping("/streets")
@CrossOrigin
class StreetController(
    @Autowired
    val streetServiceImpl: StreetServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [Street] entities
     */
    @GetMapping
    fun getAllStreets(@RequestParam(required = false) withTitles: Boolean): ResponseEntity<List<Any>> {
        return ResponseEntity(streetServiceImpl.getAllStreets(withTitles), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [Street] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [Street] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getStreetById(@RequestParam value: String) : ResponseEntity<Street> {
        val updatedId = try {
            value.toBigInteger()
        } catch (ex: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val street = streetServiceImpl.getStreetById(updatedId)
        return if (street == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(street, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to get a [Street] entity by its name
     * @param value the value of the title parameter
     * @return the [Street] entity, if there is one, otherwise NOT_FOUND
     */
    @GetMapping("/title")
    fun getStreetByTitle(@RequestParam value: String) : ResponseEntity<Street> {
        val street = streetServiceImpl.getStreetByTitle(value)

        return if (street == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(street, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [Street] entity to the database
     * @param streetDTO basic information about the new [Street] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addStreet(@RequestBody streetDTO: StreetDTO) : ResponseEntity<Street> {
        return try {
            val savedStreet = streetServiceImpl.addStreet(streetDTO)
            ResponseEntity(savedStreet, HttpStatus.CREATED)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Processing a request to delete an [Street] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [Street] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun removeStreetById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (ex: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            streetServiceImpl.removeStreetById(updatedId)
            HttpStatus.OK
        } catch (ex: IllegalStateException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [Street] entity data in the database
     * @param street basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateStreet(@RequestBody street: Street) : ResponseEntity<Street> {
        return try {
            val response = streetServiceImpl.updateStreet(street)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}