package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.PhoneHistory
import com.example.springoraclehibernate.services.implementations.PhoneHistoryServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for processing requests for manipulating [PhoneHistory] entities
 * @property phoneHistoryServiceImpl a set of methods for manipulating [PhoneHistory] entities
 */
@RestController
@RequestMapping("/history")
@CrossOrigin
class PhoneHistoryController(
    @Autowired
    private val phoneHistoryServiceImpl: PhoneHistoryServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [PhoneHistory] entities
     */
    @GetMapping
    fun getHistory() : ResponseEntity<List<PhoneHistory>> {
        return ResponseEntity(phoneHistoryServiceImpl.getHistory(), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [PhoneHistory] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [PhoneHistory] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getHistoryById(@RequestParam value: String) : ResponseEntity<PhoneHistory> {
        val updatedId = try {
            value.toLong()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val history = phoneHistoryServiceImpl.getHistoryById(updatedId)
        return if (history == null) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        } else {
            ResponseEntity(history, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to get objects of the [PhoneHistory] class only for an object
     * of the person class with a specific id
     * @param personID id of the Person class object to find records for
     * @return an object of the [PhoneHistory] class with the specified [personID], if there is one;
     * otherwise NOT_FOUND
     */
    @GetMapping("/pid")
    fun getHistoryByPersonId(@RequestParam personID: String) : ResponseEntity<PhoneHistory> {
        val updatedId = try {
            personID.toLong()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val history = phoneHistoryServiceImpl.getHistoryByPersonID(updatedId)
        return if (history == null) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        } else {
            ResponseEntity(history, HttpStatus.OK)
        }
    }
}