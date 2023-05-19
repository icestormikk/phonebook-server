package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.City
import com.example.springoraclehibernate.domain.dto.CityDTO
import java.math.BigInteger

interface CityService {
    fun getAllCities(withTitles: Boolean = false) : List<Any>
    fun getCityById(id: BigInteger) : City?
    fun getCityByTitle(title: String) : City?
    fun addCity(cityDTO: CityDTO) : City
    fun updateCity(city: City) : City
    fun removeCityById(id: BigInteger)
}