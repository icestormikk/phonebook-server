package com.example.springoraclehibernate.services

import org.springframework.data.repository.CrudRepository
import java.math.BigInteger

/**
 * Retrieves an entity with the specified id from the specified repository
 * @param T type of values being taken and received
 * @param id the unique identifier of the entity
 * @param repository the repository from which the entity needs to be retrieved
 * @param entity name of the entity being taken
 * @return
 */
fun <T> getFromRepositoryById(id: BigInteger, repository: CrudRepository<T, BigInteger>, entity: String): T =
    repository
        .findById(id)
        .orElseThrow {
            error("The $entity with id $id does not exist in the database!")
        }