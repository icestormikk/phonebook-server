package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.Street
import com.example.springoraclehibernate.domain.dto.StreetDTO
import java.math.BigInteger

interface StreetService {
    fun getAllStreets(withTitles: Boolean = false) : List<Any>
    fun getStreetById(id: BigInteger) : Street?
    fun getStreetByTitle(title: String) : Street?
    fun addStreet(streetDTO: StreetDTO): Street
    fun updateStreet(street: Street) : Street
    fun removeStreetById(id: BigInteger)
}