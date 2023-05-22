package com.example.springoraclehibernate.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for processing auxiliary, unimportant requests
 */
@RestController
@RequestMapping("/")
@CrossOrigin
class MainController {
    /**
     * Checking server availability
     * @return a line with the corresponding message for the user
     */
    @GetMapping
    fun ping() : ResponseEntity<String> =
        ResponseEntity("I'm alive!", HttpStatus.OK)
}