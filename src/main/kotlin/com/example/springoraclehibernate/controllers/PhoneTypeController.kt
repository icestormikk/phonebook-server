package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.PhoneType
import com.example.springoraclehibernate.services.implementations.PhoneTypeServiceImpl
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
 * Controller for processing requests for manipulating [PhoneType] entities
 * @property phoneTypeServiceImpl a set of methods for manipulating [PhoneType] entities
 */
@RestController
@RequestMapping("/types")
@CrossOrigin
class PhoneTypeController(
    @Autowired
    private val phoneTypeServiceImpl: PhoneTypeServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [PhoneType] entities
     */
    @GetMapping
    fun getAllTypes() : ResponseEntity<List<PhoneType>> {
        return ResponseEntity(phoneTypeServiceImpl.getAllTypes(), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [PhoneType] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [PhoneType] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getTypeById(@RequestParam value: String) : ResponseEntity<PhoneType> {
        val updatedId = try {
            value.toBigInteger()
        } catch (ex: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val type = phoneTypeServiceImpl.getTypeById(updatedId)
        return if (type == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(type, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [PhoneType] entity to the database
     * @param type basic information about the new [PhoneType] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addType(@RequestBody type: PhoneType) : ResponseEntity<PhoneType> {
        return ResponseEntity(phoneTypeServiceImpl.addType(type), HttpStatus.OK)
    }

    /**
     * Processing a request to delete an [PhoneType] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [PhoneType] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun deleteTypeById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            phoneTypeServiceImpl.deleteType(updatedId)
            HttpStatus.OK
        } catch (_: NumberFormatException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [PhoneType] entity data in the database
     * @param type basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateType(@RequestParam type: PhoneType) : ResponseEntity<PhoneType> {
        return try {
            val response = phoneTypeServiceImpl.updateType(type)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}