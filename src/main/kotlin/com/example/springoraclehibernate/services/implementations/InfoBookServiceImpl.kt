package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
import com.example.springoraclehibernate.repositories.AddressRepository
import com.example.springoraclehibernate.repositories.CategoryRepository
import com.example.springoraclehibernate.repositories.InfoBookRepository
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.services.InfoBookService
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
    private val addressRepository: AddressRepository
) : InfoBookService {
    override fun getAllInfos(): List<InfoBook> {
        return infoBookRepository.findAll().toList()
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

    override fun getInfosByEmail(email: String): List<InfoBook> {
        return infoBookRepository.getAllByEmail(email)
    }

    override fun getInfosByISQId(isqId: BigInteger): List<InfoBook> {
        return infoBookRepository.getAllByISQID(isqId)
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

        return infoBookRepository.save(
            InfoBook(
                phoneNumber = infoBookDTO.phone,
                email = infoBookDTO.email,
                ISQID = infoBookDTO.ISQID,
                personID = infoBookDTO.personID,
                categoryID = infoBookDTO.categoryID,
                addressID = infoBookDTO.addressID,
                refPerson = person.get(),
                refCategory = category.get(),
                refAddress = address.get()
            )
        )
    }

    override fun removeInfoById(id: BigInteger) {
        val isCountryExist = infoBookRepository.existsById(id)

        if (!isCountryExist) {
            error("The infos with id $id does not exist in the database!")
        }
        infoBookRepository.deleteById(id)
    }
}