package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.PhoneType
import com.example.springoraclehibernate.repositories.PhoneTypeRepository
import com.example.springoraclehibernate.services.PhoneTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PhoneTypeServiceImpl(
    @Autowired
    private val phoneTypeRepository: PhoneTypeRepository
) : PhoneTypeService {
    override fun getAllTypes(): List<PhoneType> {
        return phoneTypeRepository.findAll().toList()
    }

    override fun getTypeById(id: Long): PhoneType? {
        val type = phoneTypeRepository.findById(id)

        if (type.isEmpty) {
            return null
        }
        return type.get()
    }

    override fun addType(type: PhoneType): PhoneType {
        return phoneTypeRepository.save(type)
    }

    override fun deleteType(id: Long) {
        val isTypeExist = phoneTypeRepository.existsById(id)

        if (!isTypeExist) {
            error("The street with id $id does not exist in the database!")
        }

        phoneTypeRepository.deleteById(id)
    }

    override fun updateType(type: PhoneType): PhoneType {
        if (type.id == null) {
            error("Id can not be null!")
        }

        val isCountryExist = phoneTypeRepository.existsById(type.id!!)
        if (!isCountryExist) {
            error("The country with id ${type.id} does not exist in the database!")
        }

        return phoneTypeRepository.save(type)
    }
}