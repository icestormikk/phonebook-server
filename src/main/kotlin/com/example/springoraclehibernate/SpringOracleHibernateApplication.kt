package com.example.springoraclehibernate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringOracleHibernateApplication

fun main(args: Array<String>) {
    runApplication<SpringOracleHibernateApplication>(*args)
}

