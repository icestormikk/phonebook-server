package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.Category
import com.example.springoraclehibernate.domain.dto.CategoryDTO
import com.example.springoraclehibernate.services.implementations.CategoryServiceImpl
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

/**
 * Controller for processing requests for manipulating [Category] entities
 * @property categoryServiceImpl a set of methods for manipulating [Category] entities
 */
@RestController
@RequestMapping("/categories")
@CrossOrigin
class CategoryController(
    @Autowired
    private val categoryServiceImpl: CategoryServiceImpl
) {
    /**
     * Processing a request to get all entities
     * @return list of [Category] entities
     */
    @GetMapping
    fun getAllCategories() : ResponseEntity<List<Category>> {
        return ResponseEntity(categoryServiceImpl.getAllCategories(), HttpStatus.OK)
    }

    /**
     * Processing a request to get an [Category] entity by its unique id
     * @param value the value of the id parameter of the desired entity
     * @return BAD_REQUEST, if the ID format is incorrect; the [Category] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @GetMapping("/id")
    fun getCategoryById(@RequestParam value: String) : ResponseEntity<Category> {
        val updatedId = try {
            value.toBigInteger()
        } catch (_: NumberFormatException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val category = categoryServiceImpl.getCategoryById(updatedId)
        return if (category == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(category, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to get a [Category] entity by its name
     * @param value the value of the title parameter
     * @return the [Category] entity, if there is one, otherwise NOT_FOUND
     */
    @GetMapping("/title")
    fun getCategoryByTitle(@RequestParam value: String) : ResponseEntity<Category> {
        val category = categoryServiceImpl.getCategoryByTitle(value)
        return if (category == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(category, HttpStatus.OK)
        }
    }

    /**
     * Processing a request to add an [Category] entity to the database
     * @param categoryDTO basic information about the new [Category] entity
     * @return a newly created entity
     */
    @PostMapping
    fun addCategory(@RequestBody categoryDTO: CategoryDTO) : ResponseEntity<Category> {
        val category = categoryServiceImpl.addCategory(Category(title = categoryDTO.title))
        return ResponseEntity(category, HttpStatus.OK)
    }

    /**
     * Processing a request to delete an [Category] entity from the database by its unique id
     * @param id the value of the id parameter of the entity being deleted
     * @return BAD_REQUEST, if the ID format is incorrect; the [Category] entity,
     * if there is one; otherwise NOT_FOUND
     */
    @DeleteMapping
    fun removeCategoryById(@RequestParam id: String) : HttpStatus {
        val updatedId = try {
            id.toBigInteger()
        } catch (ex: NumberFormatException) {
            return HttpStatus.BAD_REQUEST
        }

        return try {
            categoryServiceImpl.removeCategoryById(updatedId)
            HttpStatus.OK
        } catch (_: IllegalStateException) {
            HttpStatus.NOT_FOUND
        }
    }

    /**
     * Processing a request to change [Category] entity data in the database
     * @param category basic information to be applied to the entity
     * @return an entity with updated data
     */
    @PutMapping
    fun updateCategory(@RequestBody category: Category) : ResponseEntity<Category> {
        return try {
            val response = categoryServiceImpl.updateCategory(category)
            ResponseEntity(response, HttpStatus.OK)
        } catch (_: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}