package com.example.springoraclehibernate

import com.example.springoraclehibernate.services.implementations.FilesStorageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringOracleHibernateApplication(
    @Autowired
    private val storageServiceImpl: FilesStorageServiceImpl
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        storageServiceImpl.initiate()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringOracleHibernateApplication>(*args)
}

