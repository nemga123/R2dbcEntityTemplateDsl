package com.example.r2dbcentitytemplatedsl

import com.example.r2dbcentitytemplatedsl.repository.SampleDslRepository
import com.example.r2dbcentitytemplatedsl.repository.SampleRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringBootTest
class R2dbcEntityTemplateDslApplicationTests {
    @Autowired
    lateinit var sampleRepository: SampleRepository
    @Autowired
    lateinit var sampleDslRepository: SampleDslRepository

    @Test
    fun contextLoads() {
    }

    @Test
    fun selectOneTest(){
        runBlocking {
            val entity = sampleRepository.getOne(1, "name1")
            val dslEntity = sampleDslRepository.getOne(1, "name1")

            assertNotNull(entity)
            assertNotNull(dslEntity)
            assertEquals(entity, dslEntity)


            val nullEntity = sampleDslRepository.getOne(2, "name1")
            assertNull(nullEntity)
        }
    }

}
