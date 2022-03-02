package com.github.sumeta.springboot.webflux.mutidb.features.member

import com.github.sumeta.springboot.webflux.mutidb.features.customer.CustomerEntity
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.r2dbc.core.*
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(
        @Qualifier("memberDatabaseClientTemplate") private val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    suspend fun getAll() =
        r2dbcEntityTemplate.select(MemberEntity::class.java).flow()

    suspend fun get(id: String) =
        r2dbcEntityTemplate.select(
                Query.query(Criteria.where(MemberEntity::id.name).`is`(id)),
                MemberEntity::class.java
        ).awaitSingle()

    suspend fun add(memberEntity: MemberEntity) =
        r2dbcEntityTemplate.runCatching {
            insert(MemberEntity::class.java).usingAndAwait(memberEntity)
        }.onFailure {
            println("Failure")
        }.getOrThrow()

    suspend fun edit(memberEntity: MemberEntity) =
            r2dbcEntityTemplate.runCatching {
                update(memberEntity).awaitSingle()
            }.onFailure {
                println("Failure")
            }.getOrThrow()

    suspend fun delete(id: String) =
        r2dbcEntityTemplate.delete(
            Query.query(
                Criteria.where(MemberEntity::id.name).`is`(id)
            ),
            MemberEntity::class.java
        ).awaitSingle()


}