package com.github.sumeta.springboot.webflux.mutidb.features.customer

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.r2dbc.core.*
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    @Qualifier("customerDatabaseClientTemplate") private val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    suspend fun get(id: String) =
        r2dbcEntityTemplate.select(
            Query.query(Criteria.where(CustomerEntity::identifier.name).`is`(id)),
            CustomerEntity::class.java
        ).awaitSingle()

    suspend fun add(customerEntity: CustomerEntity) =
        r2dbcEntityTemplate.insert(CustomerEntity::class.java).usingAndAwait(customerEntity)

}