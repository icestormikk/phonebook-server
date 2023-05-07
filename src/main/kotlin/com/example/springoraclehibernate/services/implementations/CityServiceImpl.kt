package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.repositories.CityRepository
import com.example.springoraclehibernate.services.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class CityServiceImpl(
    @Autowired
    private val cityRepository: CityRepository
) : CityService {
    override fun getAllCities(): List<City> {
        return cityRepository.findAll().toList()
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

    override fun addCity(city: City): City {
        return cityRepository.save(city)
    }

    override fun removeCityById(id: BigInteger) {
        val isCityExist = cityRepository.existsById(id)

        if (!isCityExist) {
            error("The city with id $id does not exist in the database!")
        }
        cityRepository.deleteById(id)
    }
}