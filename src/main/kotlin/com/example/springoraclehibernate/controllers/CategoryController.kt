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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
@CrossOrigin
class CategoryController(
    @Autowired
    private val categoryServiceImpl: CategoryServiceImpl
) {
    @GetMapping
    fun getAllCategories() : ResponseEntity<List<Category>> {
        return ResponseEntity(categoryServiceImpl.getAllCategories(), HttpStatus.OK)
    }

    @GetMapping("/id")
    fun getCategoryById(@RequestParam id: String) : ResponseEntity<Category> {
        val updatedId = try {
            id.toBigInteger()
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

    @GetMapping("/title")
    fun getCategoryByTitle(@RequestParam title: String) : ResponseEntity<Category> {
        val category = categoryServiceImpl.getCategoryByTitle(title)
        return if (category == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(category, HttpStatus.OK)
        }
    }

    @PostMapping
    fun addCategory(@RequestBody categoryDTO: CategoryDTO) : ResponseEntity<Category> {
        val category = categoryServiceImpl.addCategory(Category(title = categoryDTO.title))
        return ResponseEntity(category, HttpStatus.OK)
    }

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
            HttpStatus.BAD_REQUEST
        }
    }
}