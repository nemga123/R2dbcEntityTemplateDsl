package com.example.r2dbcentitytemplatedsl.repository

import com.example.r2dbcentitytemplatedsl.entity.SampleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.withContext
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Repository

@Repository
class SampleRepository(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
) {
    suspend fun getOne(id: Long, name: String): SampleEntity? {
        return withContext(Dispatchers.IO) {
            r2dbcEntityTemplate.selectOne(
                Query.query(
                    Criteria.where("id").isEqual(id).and("name").isEqual(name)
                ),
                SampleEntity::class.java,
            ).awaitSingleOrNull()
        }
    }
}