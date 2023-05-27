package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
import com.example.springoraclehibernate.repositories.AddressRepository
import com.example.springoraclehibernate.repositories.CategoryRepository
import com.example.springoraclehibernate.repositories.InfoBookRepository
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.repositories.PhoneTypeRepository
import com.example.springoraclehibernate.services.InfoBookService
import com.example.springoraclehibernate.services.getFromRepositoryById
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class InfoBookServiceImpl(
    @Autowired
    private val infoBookRepository: InfoBookRepository,
    @Autowired
    private val personRepository: PersonRepository,
    @Autowired
    private val categoryRepository: CategoryRepository,
    @Autowired
    private val addressRepository: AddressRepository,
    @Autowired
    private val typesRepository: PhoneTypeRepository
) : InfoBookService {
    override fun getAllInfos(withTitles: Boolean): List<Any> {
        return if (withTitles) {
            infoBookRepository.findAllWithTitles()
        } else {
            infoBookRepository.findAll().toList()
        }
    }

    override fun getInfoById(id: BigInteger): InfoBook? {
        val info = infoBookRepository.findById(id)

        return if (info.isEmpty) {
            null
        } else {
            info.get()
        }
    }

    override fun getInfosByPhoneNumber(phoneNumber: String): List<InfoBook> {
        return infoBookRepository.getAllByPhoneNumber(phoneNumber)
    }

    override fun getAllByInitials(name: String?, surname: String?, patronymic: String?, category: String): List<InfoBook> {
        return infoBookRepository.findAllByInitials(name, surname, patronymic, category)
    }

    override fun addInfo(infoBookDTO: InfoBookDTO): InfoBook {
        val person = personRepository.findById(infoBookDTO.personID)
        if (person.isEmpty) {
            error("The person with id ${infoBookDTO.personID} does not exist in the database!")
        }

        val category = categoryRepository.findById(infoBookDTO.categoryID)
        if (category.isEmpty) {
            error("The category with id ${infoBookDTO.categoryID} does not exist in the database!")
        }

        val address = addressRepository.findById(infoBookDTO.addressID)
        if (address.isEmpty) {
            error("The address with id ${infoBookDTO.addressID} does not exist in the database!")
        }

        val type = typesRepository.findById(infoBookDTO.phoneTypeID)
        if (type.isEmpty) {
            error("The type with id ${infoBookDTO.addressID} does not exist in the database!")
        }

        return infoBookRepository.save(
            InfoBook(
                phoneNumber = infoBookDTO.phoneNumber,
                personID = infoBookDTO.personID,
                categoryID = infoBookDTO.categoryID,
                addressID = infoBookDTO.addressID,
                phoneType = infoBookDTO.phoneTypeID,
                refPerson = person.get(),
                refCategory = category.get(),
                refAddress = address.get()
            )
        )
    }

    override fun updateInfo(infoBookDTO: InfoBookDTO): InfoBook {
        if (infoBookDTO.id == null) {
            error("Id can not be null")
        }

        val info = getFromRepositoryById(infoBookDTO.id!!, infoBookRepository, "info")
        val person = getFromRepositoryById(infoBookDTO.personID, personRepository, "person")
        val category = getFromRepositoryById(infoBookDTO.categoryID, categoryRepository, "category")
        val address = getFromRepositoryById(infoBookDTO.addressID, addressRepository, "address")
        val type = getFromRepositoryById(infoBookDTO.phoneTypeID, typesRepository, "type")

        info.phoneNumber = infoBookDTO.phoneNumber
        info.personID = infoBookDTO.personID
        info.categoryID = infoBookDTO.categoryID
        info.addressID = infoBookDTO.addressID
        info.phoneType = type.id
        info.refPerson = person
        info.refCategory = category
        info.refAddress = address

        return infoBookRepository.save(info)
    }

    override fun removeInfoById(id: BigInteger) {
        val isCountryExist = infoBookRepository.existsById(id)

        if (!isCountryExist) {
            error("The infos with id $id does not exist in the database!")
        }
        infoBookRepository.deleteById(id)
    }
}