package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.Street
import java.math.BigInteger

interface StreetService {
    fun getAllStreets() : List<Street>
    fun getStreetById(id: BigInteger) : Street?
    fun getStreetByTitle(title: String) : Street?
    fun addStreet(street: Street): Street
    fun removeStreetById(id: BigInteger)
}