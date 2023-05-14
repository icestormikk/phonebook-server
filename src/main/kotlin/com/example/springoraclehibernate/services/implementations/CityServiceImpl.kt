package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.domain.dto.CityDTO
import com.example.springoraclehibernate.repositories.CityRepository
import com.example.springoraclehibernate.repositories.CountryRepository
import com.example.springoraclehibernate.services.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class CityServiceImpl(
    @Autowired
    private val countryRepository: CountryRepository,
    @Autowired
    private val cityRepository: CityRepository
) : CityService {
    override fun getAllCities(withTitles: Boolean): List<Any> {
        return if (withTitles) {
            cityRepository.findAllWithTitles()
        } else {
            cityRepository.findAll().toList()
        }
    }

    override fun getCityById(id: BigInteger): City? {
        val city = cityRepository.findById(id)

        return if (city.isEmpty) {
            null
        } else {
            city.get()
        }
    }

    override fun getCityByTitle(title: String): City? {
        val city = cityRepository.findCityByTitle(title)

        return if (city.isEmpty) {
            null
        } else {
            city.get()
        }
    }

    override fun addCity(cityDTO: CityDTO): City {
        val country = countryRepository.findById(cityDTO.countryID)

        if (country.isEmpty) {
            error("The country with id ${cityDTO.countryID} does not exist in the database!")
        }

        return cityRepository.save(City(title = cityDTO.title, countryID = cityDTO.countryID))
    }

    override fun removeCityById(id: BigInteger) {
        val isCityExist = cityRepository.existsById(id)

        if (!isCityExist) {
            error("The city with id $id does not exist in the database!")
        }
        cityRepository.deleteById(id)
    }
}