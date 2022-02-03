package com.github.sumeta.springboot.webflux.mutidb.features.customer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(value = "CUSTOMER")
data class CustomerEntity(
    @Id val identifier: String,
    val firstName: String,
    val lastName: String,
    val createdBy: String,
    val createdDate: LocalDateTime,
    val lastModifiedBy: String,
    val lastModifiedDate: LocalDateTime? = LocalDateTime.now()
)