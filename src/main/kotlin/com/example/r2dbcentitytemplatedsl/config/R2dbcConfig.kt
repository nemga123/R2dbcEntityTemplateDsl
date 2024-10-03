package com.example.r2dbcentitytemplatedsl.config

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import java.time.Duration
import java.time.ZoneId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class R2dbcConfig {
    @Bean
    @Primary
    fun paymentDBConnectionFactory(): ConnectionFactory {
        return MySqlConnectionFactory.from(
            MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .user("sample")
                .database("sample")
                .serverZoneId(ZoneId.of("UTC"))
                .connectTimeout(Duration.ofSeconds(3))
                .build()
        )
    }}