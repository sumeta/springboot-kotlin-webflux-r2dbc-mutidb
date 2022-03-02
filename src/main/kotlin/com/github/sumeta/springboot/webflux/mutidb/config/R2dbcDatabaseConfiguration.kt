package com.github.sumeta.springboot.webflux.mutidb.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.SqlServerDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


@Configuration
@EnableR2dbcRepositories
class R2dbcDatabaseConfiguration(
        @Value("\${spring.r2dbc.member.url}") private val memberUrl: String,
        @Value("\${spring.r2dbc.member.username}") private val memberUsername: String,
        @Value("\${spring.r2dbc.member.password}") private val memberPassword: String,
        @Value("\${spring.r2dbc.customer.url}") private val customerUrl: String,
        @Value("\${spring.r2dbc.customer.username}") private val customerUsername: String,
        @Value("\${spring.r2dbc.customer.password}") private val customerPassword: String
) {


    @Bean("memberConnectionFactory")
    fun memberConnectionFactory(): ConnectionFactory {
        val options = ConnectionFactoryOptions.parse(memberUrl)
                .mutate()
                .option(USER, memberUsername)
                .option(PASSWORD, memberPassword)
                .build()
        return ConnectionFactories.get(options)
    }

    @Bean("memberDatabaseClient")
    fun memberDatabaseClient(@Qualifier("memberConnectionFactory") connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean("memberDatabaseClientTemplate")
    fun memberDatabaseClientTemplate(@Qualifier("memberDatabaseClient") databaseClient: DatabaseClient): R2dbcEntityTemplate {
        val strategy = DefaultReactiveDataAccessStrategy(SqlServerDialect.INSTANCE)
        return R2dbcEntityTemplate(databaseClient, strategy)
    }

    @Bean("customerConnectionFactory")
    fun customerConnectionFactory(): ConnectionFactory {
        val options = ConnectionFactoryOptions.parse(customerUrl)
            .mutate()
            .option(USER, customerUsername)
            .option(PASSWORD, customerPassword)
            .build()
        return ConnectionFactories.get(options)
    }

    @Bean("customerDatabaseClient")
    fun customerDatabaseClient(@Qualifier("customerConnectionFactory") connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean("customerDatabaseClientTemplate")
    fun customerDatabaseClientTemplate(@Qualifier("customerDatabaseClient") databaseClient: DatabaseClient): R2dbcEntityTemplate {
        val strategy = DefaultReactiveDataAccessStrategy(SqlServerDialect.INSTANCE)
        return R2dbcEntityTemplate(databaseClient, strategy)
    }

}