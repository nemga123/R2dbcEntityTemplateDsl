package com.example.r2dbcentitytemplatedsl.dsl

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

fun <T : Any> R2dbcEntityTemplate.selectOne(clazz: KClass<T>, selectQuery: SelectQuery.() -> Unit): Mono<T> {
    return this.selectOne(
        SelectQuery().apply(selectQuery).toQuery(),
        clazz.java
    )
}