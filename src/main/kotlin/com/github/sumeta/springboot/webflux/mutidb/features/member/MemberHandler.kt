package com.github.sumeta.springboot.webflux.mutidb.features.member

import com.github.sumeta.springboot.webflux.mutidb.features.member.dto.MemberRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class MemberHandler(private val memberRepository: MemberRepository) {

    suspend fun getAll(serverRequest: ServerRequest):ServerResponse{
        println(serverRequest)
        return ServerResponse.ok().bodyAndAwait(memberRepository.getAll())
    }

    suspend fun get(serverRequest: ServerRequest):ServerResponse{
        println(serverRequest)
        memberRepository.get(serverRequest.pathVariable("id"))?.let {
            return ServerResponse.ok().bodyValueAndAwait(it)
        }
        return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun add(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<MemberRequest>().let {
                kotlin.runCatching {
                    memberRepository.add(
                        MemberEntity(
                            id= it.id,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            createdBy = "Top"
                        )
                    )
                }.onSuccess {
                    return ServerResponse.ok().bodyValueAndAwait("Success")
                }.onFailure {
                    return ServerResponse.badRequest().buildAndAwait()
                }
            }
        )

    suspend fun edit(serverRequest: ServerRequest) =
            ServerResponse.ok().json().bodyValueAndAwait(
                    serverRequest.awaitBody<MemberRequest>().let {
                        kotlin.runCatching {
                            memberRepository.edit(
                                    MemberEntity(
                                            id= it.id,
                                            firstName = it.firstName,
                                            lastName = it.lastName,
                                            createdBy = "Sumeta"
                                    )
                            )
                        }.onSuccess {
                            return ServerResponse.ok().bodyValueAndAwait("Success")
                        }.onFailure {
                            return ServerResponse.badRequest().buildAndAwait()
                        }
                    }
            )

    suspend fun delete(serverRequest: ServerRequest):ServerResponse{
        return ServerResponse.ok().json().bodyValueAndAwait(memberRepository.delete(serverRequest.pathVariable("id")))
    }

}