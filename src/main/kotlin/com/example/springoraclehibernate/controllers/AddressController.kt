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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/addresses")
@CrossOrigin
class AddressController(
    @Autowired
    private val addressServiceImpl: AddressServiceImpl
) {
    @GetMapping
    fun getAllAddresses(@RequestParam(required = false) withTitles: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(addressServiceImpl.getAllAddresses(withTitles), HttpStatus.OK)
    }

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

    @PostMapping
    fun addAddress(@RequestBody addressDTO: AddressDTO) : ResponseEntity<Address> {
        return try {
            val result = addressServiceImpl.addAddress(addressDTO)
            ResponseEntity(result, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

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
            HttpStatus.BAD_REQUEST
        }
    }
}