package com.github.sumeta.springboot.webflux.mutidb.features.member

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class MemberRouter {

    @Bean
    fun memberRoutes(
            memberHandler: MemberHandler
    ) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "member".nest {
                GET("/get", memberHandler::getAll)
                GET("/get/{id}", memberHandler::get)
                POST("/add", memberHandler::add)
                PATCH("/edit",memberHandler::edit)
                DELETE("/delete/{id}",memberHandler::delete)
            }
        }
    }
}