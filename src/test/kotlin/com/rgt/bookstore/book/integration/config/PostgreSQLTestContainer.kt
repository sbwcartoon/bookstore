package com.rgt.bookstore.book.integration.config

import org.testcontainers.containers.PostgreSQLContainer

object PostgreSQLTestContainer {
    val instance: PostgreSQLContainer<*> by lazy {
        PostgreSQLContainer("postgres:15.3").apply {
            withDatabaseName("test-db")
            withUsername("test")
            withPassword("test")
        }
    }
}