package com.example.r2dbcentitytemplatedsl.dsl

import org.springframework.data.domain.Sort.Order
import org.springframework.data.domain.Sort.by
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query


@R2dbcDsl
class SelectQuery {

    private var criteria: Criteria = Criteria.empty()

    fun toQuery(): Query {
        var query = if (!criteria.isEmpty) {
            Query.query(criteria)
        } else {
            Query.empty()
        }


        return Query.query(criteria)
    }

    fun where(criteriaBuilder: CriteriaBuilder.() -> Unit) {
        if (!criteria.isEmpty) {
            throw RuntimeException("Already defined where Statement")
        }
        this.criteria = Criteria.empty().and(CriteriaBuilder().apply(criteriaBuilder).criteriaList)
    }

}