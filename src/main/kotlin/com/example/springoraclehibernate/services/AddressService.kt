package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.address.Address
import com.example.springoraclehibernate.domain.dto.AddressDTO
import java.math.BigInteger

interface AddressService {
    fun getAllAddresses(withTitles: Boolean = false) : List<Any>
    fun getAddressById(id: BigInteger) : Address?
    fun addAddress(addressDTO: AddressDTO) : Address
    fun removeAddressById(id: BigInteger)
}