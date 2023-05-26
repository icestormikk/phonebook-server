package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
import com.example.springoraclehibernate.services.implementations.InfoBookServiceImpl
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
 * Controller for processing requests for manipulating [InfoBook] entities
 * @property infoBookServiceImpl a set of methods for manipulating [InfoBook] entities
 */
@RestController
@RequestMapping("/infos")
@CrossOrigin
class InfoBookController(
    @Autowired
    private val infoBookServiceImpl: InfoBookServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [InfoBook] entities
     */
    @GetMapping
    fun getAllInfos(@RequestParam(required = false) withMoreInfo: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(infoBookServiceImpl.getAllInfos(withMoreInfo), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [InfoBook] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [InfoBook] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getInfoById(@RequestParam value: String) : ResponseEntity<InfoBook> {
        val updatedId = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val info = infoBookServiceImpl.getInfoById(updatedId)
        return if (info == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(info, HttpStatus.OK)
        }
    }

    /**
     * Processing a request for a list of people based on their first name,
     * last name and patronymic (if any)
     * @param name the name of the people to search for
     * @param surname the surname of the people to search for
     * @param patronymic the patronymic of the people to search for
     * @return a list of people who fit at least one search criteria
     */
    @GetMapping("/initials")
    fun getInfosByInitials(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) surname: String?,
        @RequestParam(required = false) patronymic: String?,
        @RequestParam category: String
    ) : ResponseEntity<List<InfoBook>> {
        return ResponseEntity(
            infoBookServiceImpl.getAllByInitials(name, surname, patronymic, category),
            HttpStatus.OK
        )
    }

    /**
     * Processing a request to receive an [InfoBook] entity by a unique phone number for each record
     * @param value the value of the parameter storing the phone number
     * @return list of [InfoBook] entities with the specified phone number
     */
    @GetMapping("/phone")
    fun getInfosByPhone(@RequestParam value: String) : ResponseEntity<List<InfoBook>> {
        return ResponseEntity(infoBookServiceImpl.getInfosByPhoneNumber(value), HttpStatus.OK)
    }

    /**
     * Processing a request to add an [InfoBook] entity to the database
     * @param infoBookDTO basic information about the new [InfoBook] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addInfo(@RequestBody infoBookDTO: InfoBookDTO) : ResponseEntity<InfoBook> {
        return try {
            val result = infoBookServiceImpl.addInfo(infoBookDTO)
            ResponseEntity(result, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Processing a request to delete an [InfoBook] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [InfoBook] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun removeInfoById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            infoBookServiceImpl.removeInfoById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [InfoBook] entity data in the database
     * @param infoBookDTO basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateInfo(@RequestBody infoBookDTO: InfoBookDTO) : ResponseEntity<InfoBook> {
        return try {
            val response = infoBookServiceImpl.updateInfo(infoBookDTO)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}