package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.Category
import java.math.BigInteger

interface CategoryService {
    fun getAllCategories() : List<Category>
    fun getCategoryById(id: BigInteger) : Category?
    fun getCategoryByTitle(title: String) : Category?
    fun addCategory(category: Category) : Category
    fun updateCategory(category: Category) : Category
    fun removeCategoryById(id: BigInteger)
}