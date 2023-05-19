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

@RestController
@RequestMapping("/history")
@CrossOrigin
class PhoneHistoryController(
    @Autowired
    private val phoneHistoryServiceImpl: PhoneHistoryServiceImpl
) {
    @GetMapping
    fun getHistory() : ResponseEntity<List<PhoneHistory>> {
        return ResponseEntity(phoneHistoryServiceImpl.getHistory(), HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getHistoryById(@RequestParam id: String) : ResponseEntity<PhoneHistory> {
        val updatedId = try {
            id.toLong()
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