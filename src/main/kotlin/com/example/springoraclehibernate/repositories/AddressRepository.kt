package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface AddressRepository : CrudRepository<Address, BigInteger>
