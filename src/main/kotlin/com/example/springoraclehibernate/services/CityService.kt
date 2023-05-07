package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.City
import java.math.BigInteger

interface CityService {
    fun getAllCities() : List<City>
    fun getCityById(id: BigInteger) : City?
    fun getCityByTitle(title: String) : City?
    fun addCity(city: City) : City
    fun removeCityById(id: BigInteger)
}