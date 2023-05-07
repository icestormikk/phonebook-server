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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    fun getPersonsByName(@RequestParam name: String) : ResponseEntity<Person> {
        val result = personServiceImpl.getPersonsByName(name)

        return if (result == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(result, HttpStatus.OK)
        }
    }

    @GetMapping("/surname")
    fun getPersonsBySurname(@RequestParam surname: String) : ResponseEntity<Person> {
        val result = personServiceImpl.getPersonsBySurname(surname)

        return if (result == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(result, HttpStatus.OK)
        }
    }

    @GetMapping("/patronymic")
    fun getPersonsByPatronymic(@RequestParam patronymic: String) : ResponseEntity<Person> {
        val result = personServiceImpl.getPersonsByPatronymic(patronymic)

        return if (result == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(result, HttpStatus.OK)
        }
    }

    @PostMapping
    fun addPerson(@RequestBody personDTO: PersonDTO) : Person {
        val person = Person(
            name = personDTO.name,
            surname = personDTO.surname,
            patronymic = personDTO.patronymic
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
}