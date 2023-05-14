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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/infos")
@CrossOrigin
class InfoBookController(
    @Autowired
    private val infoBookServiceImpl: InfoBookServiceImpl
) {
    @GetMapping
    fun getAllInfos(@RequestParam(required = false) withMoreInfo: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(infoBookServiceImpl.getAllInfos(withMoreInfo), HttpStatus.OK)
    }

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

    @GetMapping("/phone")
    fun getInfosByPhone(@RequestParam value: String) : ResponseEntity<List<InfoBook>> {
        return ResponseEntity(infoBookServiceImpl.getInfosByPhoneNumber(value), HttpStatus.OK)
    }

    @PostMapping
    fun addInfo(@RequestBody infoBookDTO: InfoBookDTO) : ResponseEntity<InfoBook> {
        return try {
            val result = infoBookServiceImpl.addInfo(infoBookDTO)
            ResponseEntity(result, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

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
            HttpStatus.BAD_REQUEST
        }
    }
}