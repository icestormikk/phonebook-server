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

@RestController
@RequestMapping("/streets")
@CrossOrigin
class StreetController(
    @Autowired
    val streetServiceImpl: StreetServiceImpl
) {
    @GetMapping
    fun getAllStreets(@RequestParam(required = false) withTitles: Boolean): ResponseEntity<List<Any>> {
        return ResponseEntity(streetServiceImpl.getAllStreets(withTitles), HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getStreetById(@RequestParam id: String) : ResponseEntity<Street> {
        val updatedId = try {
            id.toBigInteger()
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

    @GetMapping("/title")
    fun getStreetByTitle(@RequestParam title: String) : ResponseEntity<Street> {
        val street = streetServiceImpl.getStreetByTitle(title)

        return if (street == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(street, HttpStatus.OK)
        }
    }

    @PostMapping
    fun addStreet(@RequestBody streetDTO: StreetDTO) : ResponseEntity<Street> {
        return try {
            val savedStreet = streetServiceImpl.addStreet(streetDTO)
            ResponseEntity(savedStreet, HttpStatus.CREATED)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

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
            HttpStatus.BAD_REQUEST
        }
    }

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