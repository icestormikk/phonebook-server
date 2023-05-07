package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.Street
import com.example.springoraclehibernate.repositories.StreetRepository
import com.example.springoraclehibernate.services.StreetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class StreetServiceImpl(
    @Autowired
    val streetRepository: StreetRepository
) : StreetService {
    override fun getAllStreets(): List<Street> {
        val result = streetRepository.findAll()
        return result.toList()
    }

    override fun getStreetById(id: BigInteger): Street? {
        val street = streetRepository.findById(id)

        if (street.isEmpty) {
            return null
        }
        return street.get()
    }

    override fun getStreetByTitle(title: String): Street? {
        val street = streetRepository.findStreetByTitle(title)

        if (street.isEmpty) {
            return null
        }
        return street.get()
    }

    override fun addStreet(street: Street) : Street {
        return streetRepository.save(street)
    }

    override fun removeStreetById(id: BigInteger) {
        val isStreetExist = streetRepository.existsById(id)

        if (!isStreetExist) {
            error("The street with id $id does not exist in the database!")
        }

        streetRepository.deleteById(id)
    }
}