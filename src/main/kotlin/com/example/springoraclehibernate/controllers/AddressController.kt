package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.address.Address
import com.example.springoraclehibernate.domain.dto.AddressDTO
import com.example.springoraclehibernate.services.implementations.AddressServiceImpl
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
 * Controller for processing requests for manipulating [Address] entities
 * @property addressServiceImpl a set of methods for manipulating [Address] entities
 */
@RestController
@RequestMapping("/addresses")
@CrossOrigin
class AddressController(
    @Autowired
    private val addressServiceImpl: AddressServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @param withTitles parameter defining the format of the response:
     * true if it is necessary to return the names of objects;
     * false if only the id is returned
     * @return list of [Address] entities
     */
    @GetMapping
    fun getAllAddresses(@RequestParam(required = false) withTitles: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(addressServiceImpl.getAllAddresses(withTitles), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [Address] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [Address] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getAddressById(@RequestParam value: String) : ResponseEntity<Address> {
        val updatedId = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val address = addressServiceImpl.getAddressById(updatedId)
        return if (address == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(address, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [Address] entity to the database
     * @param addressDTO basic information about the new [Address] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addAddress(@RequestBody addressDTO: AddressDTO) : ResponseEntity<Address> {
        return try {
            val result = addressServiceImpl.addAddress(addressDTO)
            ResponseEntity(result, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Processing a request to delete an [Address] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [Address] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun removeAddressById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            addressServiceImpl.removeAddressById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [Address] entity data in the database
     * @param addressDTO basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateAddress(@RequestBody addressDTO: AddressDTO) : ResponseEntity<Address> {
        return try {
            val result = addressServiceImpl.updateAddress(addressDTO)
            ResponseEntity(result, HttpStatus.OK)
        } catch (ex: IllegalStateException) {
            println(ex.message)
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}