package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.dto.PersonDTO
import com.example.springoraclehibernate.services.implementations.PersonServiceImpl
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
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/persons")
@CrossOrigin
class PersonController(
    @Autowired
    private val personServiceImpl: PersonServiceImpl
) {
    @GetMapping
    fun getPeople() : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPeople(), HttpStatus.OK)
    }

    @GetMapping("/name")
    fun getPersonsByName(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsByName(value), HttpStatus.OK)
    }

    @GetMapping("/surname")
    fun getPersonsBySurname(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsBySurname(value), HttpStatus.OK)
    }

    @GetMapping("/patronymic")
    fun getPersonsByPatronymic(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsByPatronymic(value), HttpStatus.OK)
    }

    @GetMapping("/isq")
    fun getPersonsByIsqId(@RequestParam value: String) : ResponseEntity<Person> {
        val updatedISQ = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val infos = personServiceImpl.getPersonByIsqId(updatedISQ)
        return ResponseEntity(infos, HttpStatus.OK)
    }

    @GetMapping("/email")
    fun getPersonByEmail(@RequestParam value: String) : ResponseEntity<Person> {
        return ResponseEntity(personServiceImpl.getPersonByEmail(value), HttpStatus.OK)
    }

    @PostMapping
    fun addPerson(@RequestBody personDTO: PersonDTO) : Person {
        val person = Person(
            name = personDTO.name,
            surname = personDTO.surname,
            patronymic = personDTO.patronymic,
            email = personDTO.email,
            isqId = personDTO.ISQID
        )
        return personServiceImpl.addPerson(person)
    }

    @DeleteMapping
    fun removePersonById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            personServiceImpl.removePersonById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.BAD_REQUEST
        }
    }

    @PostMapping("/avatar")
    fun setAvatar(
        @RequestParam("avatar") file: MultipartFile, @RequestParam("id") id: String
    ) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (_: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            personServiceImpl.setAvatar(file, updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.BAD_REQUEST
        }
    }

    @PutMapping
    fun updatePerson(@RequestBody person: Person) : ResponseEntity<Person> {
        return try {
            val response = personServiceImpl.updatePerson(person)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}