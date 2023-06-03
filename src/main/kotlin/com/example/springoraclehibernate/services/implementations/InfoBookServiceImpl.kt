package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
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

    override fun getAllByInitials(
        name: String?, surname: String?, patronymic: String?, categoryId: BigInteger
    ): List<InfoBook> {
        return infoBookRepository.findAllByInitials(name, surname, patronymic, categoryId)
    }

    override fun addInfo(infoBookDTO: InfoBookDTO): InfoBook {
        val person = getFromRepositoryById(infoBookDTO.personID, personRepository, "person")
        val type = getFromRepositoryById(infoBookDTO.phoneTypeID, typesRepository, "phone type")

        return infoBookRepository.save(
            InfoBook(
                phoneNumber = infoBookDTO.phoneNumber,
                personID = infoBookDTO.personID,
                refPerson = person,
                refPhoneType = type
            )
        )
    }

    override fun updateInfo(infoBookDTO: InfoBookDTO): InfoBook {
        if (infoBookDTO.id == null) {
            error("Id can not be null")
        }

        val info = getFromRepositoryById(infoBookDTO.id!!, infoBookRepository, "info")
        val person = getFromRepositoryById(infoBookDTO.personID, personRepository, "person")
        val type = getFromRepositoryById(infoBookDTO.phoneTypeID, typesRepository, "type")

        info.phoneNumber = infoBookDTO.phoneNumber
        info.personID = infoBookDTO.personID
        info.refPerson = person
        info.refPhoneType = type

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