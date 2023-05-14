package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.domain.dto.CityDTO
import com.example.springoraclehibernate.services.implementations.CityServiceImpl
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
@RequestMapping("/cities")
@CrossOrigin
class CityController(
    @Autowired
    private val cityServiceImpl: CityServiceImpl
) {
    @GetMapping
    fun getAllCities(@RequestParam(required = false) withTitles: Boolean) : ResponseEntity<List<Any>> {
        return ResponseEntity(cityServiceImpl.getAllCities(withTitles), HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getOneCityById(@RequestParam value: String) : ResponseEntity<City> {
        val updatedId = try {
            value.toBigInteger()
        } catch (ex: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val city = cityServiceImpl.getCityById(updatedId)

        return if (city == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(city, HttpStatus.OK)
        }
    }

    @GetMapping("/title")
    fun getOneCityByTitle(@RequestParam value: String) : ResponseEntity<City> {
        val city = cityServiceImpl.getCityByTitle(value)

        return if (city == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(city, HttpStatus.OK)
        }
    }

    @PostMapping
    fun addCity(@RequestBody cityDTO: CityDTO) : ResponseEntity<City> {
        return try {
            val savedEntity = cityServiceImpl.addCity(cityDTO)
            ResponseEntity(savedEntity, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping
    fun removeCityById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (ex: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            cityServiceImpl.removeCityById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.BAD_REQUEST
        }
    }
}