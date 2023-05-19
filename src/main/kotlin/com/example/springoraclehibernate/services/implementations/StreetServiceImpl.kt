package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.Street
import com.example.springoraclehibernate.domain.dto.StreetDTO
import com.example.springoraclehibernate.repositories.CityRepository
import com.example.springoraclehibernate.repositories.StreetRepository
import com.example.springoraclehibernate.services.StreetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class StreetServiceImpl(
    @Autowired
    private val cityRepository: CityRepository,
    @Autowired
    private val streetRepository: StreetRepository
) : StreetService {
    override fun getAllStreets(withTitles: Boolean): List<Any> {
        return if (withTitles) {
            streetRepository.findAllWithTitles()
        } else {
            streetRepository.findAll().toList()
        }
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

    override fun addStreet(streetDTO: StreetDTO) : Street {
        val city = cityRepository.findById(streetDTO.cityID)

        if (city.isEmpty) {
            error("The city with id ${streetDTO.cityID} does not exist in the database!")
        }

        return streetRepository.save(Street(title = streetDTO.title, cityID = streetDTO.cityID))
    }

    override fun updateStreet(street: Street): Street {
        if (street.id == null) {
            error("Id can not be null")
        }

        if (!streetRepository.existsById(street.id!!)) {
            error("The street with id ${street.id} does not exist in the database!")
        }

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