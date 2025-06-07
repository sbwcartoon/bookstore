package com.rgt.bookstore.book.integration.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.junit.jupiter.Testcontainers
import javax.sql.DataSource

@Testcontainers
@TestConfiguration
class TestContainerConfig {

    companion object {
        init {
            PostgreSQLTestContainer.instance.start()
        }
    }

    @Bean
    fun dataSource(): DataSource {
        val container = PostgreSQLTestContainer.instance
        return DataSourceBuilder.create()
            .url(container.jdbcUrl)
            .username(container.username)
            .password(container.password)
            .driverClassName(container.driverClassName)
            .build()
    }
}
