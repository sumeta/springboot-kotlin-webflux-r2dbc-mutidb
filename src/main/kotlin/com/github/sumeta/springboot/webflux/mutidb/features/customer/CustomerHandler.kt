package com.github.sumeta.springboot.webflux.mutidb.features.customer

import com.github.sumeta.springboot.webflux.mutidb.features.customer.dto.CustomerRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime

@Component
class CustomerHandler(private val customerRepository: CustomerRepository) {

    suspend fun get(serverRequest: ServerRequest):ServerResponse{
        println(serverRequest)
        customerRepository.get(serverRequest.pathVariable("id"))?.let {
            return ServerResponse.ok().bodyValueAndAwait(it)
        }
        return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun add(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<CustomerRequest>().let {
                kotlin.runCatching {
                    customerRepository.add(
                        CustomerEntity(
                            identifier = it.identifier,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            createdBy = it.identifier,
                            createdDate = LocalDateTime.now(),
                            lastModifiedBy = it.identifier
                        )
                    )
                }.onSuccess {
                    return ServerResponse.ok().bodyValueAndAwait("Success")
                }.onFailure {
                    return ServerResponse.badRequest().buildAndAwait()
                }
            }
        )
}
