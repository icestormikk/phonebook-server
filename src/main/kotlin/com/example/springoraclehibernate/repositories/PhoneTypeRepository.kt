package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.PhoneType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PhoneTypeRepository : CrudRepository<PhoneType, Long>