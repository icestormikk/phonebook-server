package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.Category
import com.example.springoraclehibernate.repositories.CategoryRepository
import com.example.springoraclehibernate.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class CategoryServiceImpl(
    @Autowired
    private val categoryRepository: CategoryRepository
) : CategoryService {
    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll().toList()
    }

    override fun getCategoryById(id: BigInteger): Category? {
        val category = categoryRepository.findById(id)

        return if (category.isEmpty) {
            null
        } else {
            category.get()
        }
    }

    override fun getCategoryByTitle(title: String): Category? {
        val category = categoryRepository.findCategoryByTitle(title)

        return if (category.isEmpty) {
            null
        } else {
            category.get()
        }
    }

    override fun addCategory(category: Category): Category {
        return categoryRepository.save(category)
    }

    override fun removeCategoryById(id: BigInteger) {
        val isCategoryExist = categoryRepository.existsById(id)

        if (!isCategoryExist) {
            error("The category with id $id does not exist in the database!")
        }
        categoryRepository.deleteById(id)
    }
}