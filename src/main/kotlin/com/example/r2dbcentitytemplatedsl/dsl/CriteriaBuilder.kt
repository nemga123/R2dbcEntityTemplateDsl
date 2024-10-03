package com.example.r2dbcentitytemplatedsl.dsl

import org.springframework.data.relational.core.query.Criteria
import kotlin.reflect.KProperty1

@R2dbcDsl
class CriteriaBuilder {
    val criteriaList = mutableListOf<Criteria>()

    fun and(criteria: CriteriaBuilder.() -> Unit) {
        val newCriteria = Criteria.empty().and(CriteriaBuilder().apply(criteria).criteriaList)
        criteriaList.add(newCriteria)
    }

    fun or(criteria: CriteriaBuilder.() -> Unit) {
        val newCriteriaList = CriteriaBuilder().apply(criteria).criteriaList

        var newCriteria = Criteria.empty()
        newCriteriaList.forEach {
            newCriteria = newCriteria.or(it)
        }
        criteriaList.add(newCriteria)
    }

    infix fun <T, V> KProperty1<T, V?>.isEqualTo(value: V?) where V: Comparable<V> {
        val newCriteria = if (value == null) {
            Criteria.where(this.name).isNull()
        } else {
            Criteria.where(this.name).`is`(value)
        }
        criteriaList.add(newCriteria)
    }

    infix fun <T, V> KProperty1<T, V?>.isNotEqualTo(value: V?) where V: Comparable<V> {
        val newCriteria = if (value == null) {
            Criteria.where(this.name).isNotNull()
        } else {
            Criteria.where(this.name).not(value)
        }
        criteriaList.add(newCriteria)
    }


    infix fun <T, V> KProperty1<T, V?>.isIn(values: Collection<V>) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).`in`(values))
    }

    infix fun <T, V> KProperty1<T, V?>.isNotIn(values: Collection<V>) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).notIn(values))
    }

    infix fun <T, V> KProperty1<T, V?>.between(range: Pair<V, V>) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).between(range.first, range.second))
    }

    infix fun <T, V> KProperty1<T, V?>.notBetween(range: Pair<V, V>) where V: Comparable<V>{
        criteriaList.add(Criteria.where(this.name).notBetween(range.first, range.second))
    }

    infix fun <T, V> KProperty1<T, V?>.lessThan(value: V) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).lessThan(value))
    }

    infix fun <T, V> KProperty1<T, V?>.lessThanOrEquals(value: V) where V: Comparable<V>{
        criteriaList.add(Criteria.where(this.name).lessThanOrEquals(value))
    }

    infix fun <T, V> KProperty1<T, V?>.greaterThan(value: V) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).greaterThan(value))
    }

    infix fun <T, V> KProperty1<T, V?>.greaterThanOrEquals(value: V) where V: Comparable<V> {
        criteriaList.add(Criteria.where(this.name).greaterThanOrEquals(value))
    }

    infix fun <T, V> KProperty1<T, V?>.like(value: V) where V: Comparable<V>{
        criteriaList.add(Criteria.where(this.name).like(value))
    }

    infix fun <T, V> KProperty1<T, V?>.notLike(value: V) where V: Comparable<V>{
        criteriaList.add(Criteria.where(this.name).notLike(value))
    }

}