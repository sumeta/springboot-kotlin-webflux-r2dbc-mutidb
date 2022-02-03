package com.github.sumeta.springboot.webflux.mutidb.features.customer

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.relational.core.query.Criteria
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository( @Qualifier("customerDatabaseClient") private val databaseClient: DatabaseClient) {

    suspend fun get(id:String) =
            databaseClient.select().from(CustomerEntity::class.java)
                    .matching(Criteria.where(CustomerEntity::identifier.name) .`is`(id))
                    .fetch().awaitFirstOrNull()

    suspend fun add(customerEntity: CustomerEntity) =
        databaseClient.insert()
            .into(CustomerEntity::class.java)
            .using(customerEntity)
            .fetch()
            .awaitOneOrNull()
}