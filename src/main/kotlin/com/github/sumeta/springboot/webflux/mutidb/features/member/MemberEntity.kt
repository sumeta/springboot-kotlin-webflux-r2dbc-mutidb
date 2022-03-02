package com.github.sumeta.springboot.webflux.mutidb.features.member

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(value = "MEMBER")
data class MemberEntity (
    @Id
    @Column("ID")
    val id: String,
    @Column("FIRST_NAME")
    val firstName: String,
    @Column("LAST_NAME")
    val lastName: String,
    @Column("CREATED_BY")
    val createdBy: String,
    @Column("CREATED_DATE")
    val lastModifiedDate: LocalDateTime? = LocalDateTime.now()
)
