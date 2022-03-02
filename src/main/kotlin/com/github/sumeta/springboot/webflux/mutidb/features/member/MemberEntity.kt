package com.github.sumeta.springboot.webflux.mutidb.features.member

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "MEMBER")
data class MemberEntity (
    @Id
    val id: String,
    val firstName: String,
    val lastName: String,
    val createdBy: String
)
