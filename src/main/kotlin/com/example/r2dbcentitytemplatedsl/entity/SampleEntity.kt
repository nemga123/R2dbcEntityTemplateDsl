package com.example.r2dbcentitytemplatedsl.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("sample_table")
data class SampleEntity(
    @Column("id")
    val id: Long,
    @Column("name")
    val name: String,
)