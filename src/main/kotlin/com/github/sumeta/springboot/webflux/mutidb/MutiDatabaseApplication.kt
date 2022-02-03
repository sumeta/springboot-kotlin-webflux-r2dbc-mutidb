package com.github.sumeta.springboot.webflux.mutidb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MutiDatabaseApplication

fun main(args: Array<String>) {
	runApplication<MutiDatabaseApplication>(*args)
}
