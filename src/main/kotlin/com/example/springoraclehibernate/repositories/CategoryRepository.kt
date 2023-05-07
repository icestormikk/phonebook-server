package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
interface CategoryRepository : CrudRepository<Category, BigInteger> {
    fun findCategoryByTitle(title: String) : Optional<Category>
}