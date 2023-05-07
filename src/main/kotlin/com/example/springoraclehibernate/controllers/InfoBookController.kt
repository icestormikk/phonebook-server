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
        val infos = infoBookServiceImpl.getAllInfos()

        return ResponseEntity(
            if (withMoreInfo) infos.map { it.toInfoBookWithTitles() } else infos,
            HttpStatus.OK
        )
    }

    @GetMapping("/id")
    fun getInfoById(@RequestParam id: String) : ResponseEntity<InfoBook> {
        val updatedId = try {
            id.toBigInteger()
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

    @GetMapping("/email")
    fun getInfosByEmail(@RequestParam email: String) : ResponseEntity<List<InfoBook>> {
        return ResponseEntity(infoBookServiceImpl.getInfosByEmail(email), HttpStatus.OK)
    }

    @GetMapping("/phone")
    fun getInfosByPhone(@RequestParam phone: String) : ResponseEntity<List<InfoBook>> {
        return ResponseEntity(infoBookServiceImpl.getInfosByPhoneNumber(phone), HttpStatus.OK)
    }

    @GetMapping("/isq")
    fun getInfosByISQId(@RequestParam isq: String) : ResponseEntity<List<InfoBook>> {
        val updatedISQ = try {
            isq.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val infos = infoBookServiceImpl.getInfosByISQId(updatedISQ)
        return ResponseEntity(infos, HttpStatus.OK)
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