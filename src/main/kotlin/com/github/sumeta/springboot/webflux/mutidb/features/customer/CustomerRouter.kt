package com.github.sumeta.springboot.webflux.mutidb.features.customer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouter {

    @Bean
    fun customerRoutes(
            customerHandler: CustomerHandler
    ) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "customer".nest {
                GET("/get/{id}", customerHandler::get)
                POST("/add", customerHandler::add)
            }
        }
    }
}