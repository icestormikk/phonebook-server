package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.address.Address
import com.example.springoraclehibernate.domain.dto.AddressDTO
import com.example.springoraclehibernate.repositories.AddressRepository
import com.example.springoraclehibernate.repositories.CityRepository
import com.example.springoraclehibernate.repositories.CountryRepository
import com.example.springoraclehibernate.repositories.StreetRepository
import com.example.springoraclehibernate.services.AddressService
import com.example.springoraclehibernate.services.getFromRepositoryById
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class AddressServiceImpl(
    @Autowired
    private val streetRepository: StreetRepository,
    @Autowired
    private val cityRepository: CityRepository,
    @Autowired
    private val countryRepository: CountryRepository,
    @Autowired
    private val addressRepository: AddressRepository
) : AddressService {
    override fun getAllAddresses(withTitles: Boolean): List<Any> {
        return if (withTitles) {
            addressRepository.findAllWithTitles()
        } else {
            addressRepository.findAll().toList()
        }
    }

    override fun getAddressById(id: BigInteger): Address? {
        val address = addressRepository.findById(id)

        return if (address.isEmpty) {
            null
        } else {
            address.get()
        }
    }

    override fun addAddress(addressDTO: AddressDTO): Address {
        val street = streetRepository.findById(addressDTO.streetID)
        if (street.isEmpty) {
            error("The street with id ${addressDTO.streetID} does not exist in the database!")
        }

        val city = cityRepository.findById(addressDTO.cityID)
        if (city.isEmpty) {
            error("The city with id ${addressDTO.cityID} does not exist in the database!")
        }

        val country = countryRepository.findById(addressDTO.countryID)
        if (country.isEmpty) {
            error("The country with id ${addressDTO.countryID} does not exist in the database!")
        }

        return addressRepository.save(
            Address(
                countryID = addressDTO.countryID,
                cityID = addressDTO.cityID,
                streetID = addressDTO.streetID,
                houseNumber = addressDTO.houseNumber?.toLong(),
                flatNumber = addressDTO.flatNumber?.toLong(),
                refCountry = country.get(),
                refCity = city.get(),
                refStreet = street.get()
            )
        )
    }

    override fun updateAddress(addressDTO: AddressDTO): Address {
        if (addressDTO.id == null) {
            error("Id can not be null")
        }
        val address = getFromRepositoryById(addressDTO.id, addressRepository, "address")
        val country = getFromRepositoryById(addressDTO.countryID, countryRepository, "country")
        val city = getFromRepositoryById(addressDTO.cityID, cityRepository, "city")
        val street = getFromRepositoryById(addressDTO.streetID, streetRepository, "street")

        address.countryID = addressDTO.countryID
        address.cityID = addressDTO.cityID
        address.streetID = addressDTO.streetID
        address.houseNumber = addressDTO.houseNumber?.toLong()
        address.flatNumber = addressDTO.flatNumber?.toLong()
        address.refCountry = country
        address.refCity = city
        address.refStreet = street

        return addressRepository.save(address)
    }

    override fun removeAddressById(id: BigInteger) {
        val isAddressExist = addressRepository.existsById(id)

        if (!isAddressExist) {
            error("The address with id $id does not exist in the database!")
        }

        addressRepository.deleteById(id)
    }
}