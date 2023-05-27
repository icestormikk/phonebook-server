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

@RestController
@RequestMapping("/types")
@CrossOrigin
class PhoneTypeController(
    @Autowired
    private val phoneTypeServiceImpl: PhoneTypeServiceImpl
) {
    @GetMapping
    fun getAllTypes() : ResponseEntity<List<PhoneType>> {
        return ResponseEntity(phoneTypeServiceImpl.getAllTypes(), HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getTypeById(@RequestParam value: String) : ResponseEntity<PhoneType> {
        val updatedId = try {
            value.toLong()
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

    @PostMapping
    fun addType(@RequestBody type: PhoneType) : ResponseEntity<PhoneType> {
        return ResponseEntity(phoneTypeServiceImpl.addType(type), HttpStatus.OK)
    }

    @DeleteMapping
    fun deleteTypeById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toLong()
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