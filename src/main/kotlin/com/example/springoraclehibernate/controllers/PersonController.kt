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

/**
 * Controller for processing requests for manipulating [Person] entities
 * @property personServiceImpl a set of methods for manipulating [Person] entities
 */
@RestController
@RequestMapping("/persons")
@CrossOrigin
class PersonController(
    @Autowired
    private val personServiceImpl: PersonServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [Person] entities
     */
    @GetMapping
    fun getPeople(
        @RequestParam(required = false) withTitles: Boolean
    ) : ResponseEntity<List<Any>> {
        return ResponseEntity(personServiceImpl.getPeople(withTitles), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [Person] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [Person] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getPersonById(@RequestParam value: String) : ResponseEntity<Person> {
        val updatedId = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val info = personServiceImpl.getPersonById(updatedId)
        return if (info == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(info, HttpStatus.OK)
        }
    }

    /**
     * Processing a search request for people with the specified name
     * @param value the name by which the search is performed
     * @return list of objects of the [Person] class with the desired name
     */
    @GetMapping("/name")
    fun getPersonsByName(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsByName(value), HttpStatus.OK)
    }

    /**
     * Processing a search request for people with the specified surname
     * @param value the surname by which the search is performed
     * @return list of objects of the [Person] class with the desired surname
     */
    @GetMapping("/surname")
    fun getPersonsBySurname(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsBySurname(value), HttpStatus.OK)
    }

    /**
     * Processing a search request for people with the specified patronymic
     * @param value the patronymic by which the search is performed
     * @return list of objects of the [Person] class with the desired patronymic
     */
    @GetMapping("/patronymic")
    fun getPersonsByPatronymic(@RequestParam value: String) : ResponseEntity<List<Person>> {
        return ResponseEntity(personServiceImpl.getPersonsByPatronymic(value), HttpStatus.OK)
    }

    /**
     * Processing a request to get people with a specific isqId
     * @param value the value of the isqId parameter that is being searched for
     * @return an object of the Person class whose isqId is equal to the one
     * specified in the [value] parameter
     */
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

    /**
     * Processing a request to receive a Person entity with a specific email
     * @param value the value of the email parameter that is being searched for
     * @return an object of the Person class whose email is equal to the one
     * specified in the [value] parameter
     */
    @GetMapping("/email")
    fun getPersonByEmail(@RequestParam value: String) : ResponseEntity<Person> {
        return ResponseEntity(personServiceImpl.getPersonByEmail(value), HttpStatus.OK)
    }

    /**
     *
     *
     * @param value
     * @return
     */
    @GetMapping("/phone")
    fun getPeopleByPhone(@RequestParam value: String) : ResponseEntity<Person> {
        val person = personServiceImpl.getPersonByPhone(value)

        return if (person == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(person, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [Person] entity to the database
     * @param personDTO basic information about the new [Person] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addPerson(@RequestBody personDTO: PersonDTO) : Person {
        val person = Person(
            name = personDTO.name,
            surname = personDTO.surname,
            patronymic = personDTO.patronymic,
            email = personDTO.email,
            isqId = personDTO.isqId
        )
        return personServiceImpl.addPerson(person)
    }

    /**
     * Processing a request to delete an [Person] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [Person] entity,
     * if there is one; otherwise NOT_FOUND
     */
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
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change/install an avatar for a Person object with the specified id
     * @param file a new user avatar in the form of a set of bytes
     * @param id id of the user whose avatar needs to be changed
     * @return OK, if the avatar is successfully changed, otherwise BAD_REQUEST
     */
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

    /**
     * Processing a request to change [Person] entity data in the database
     * @param personDTO basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updatePerson(@RequestBody personDTO: PersonDTO) : ResponseEntity<Person> {
        return try {
            val response = personServiceImpl.updatePerson(personDTO)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}