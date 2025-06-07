package com.rgt.bookstore.book.integration

import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.integration.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import java.util.*

@IntegrationTest
@AutoConfigureMockMvc
class DeleteBookFailureTest(
    private val mockMvc: MockMvc,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    beforeSpec {
        jpaRepository.deleteAll()
    }

    Given("책이 존재하지 않을 때") {
        When("존재하지 않는 아이디를 삭제하면") {
            Then("404 Not Found 오류가 발생한다") {
                val notExistId = UUID.randomUUID()
                mockMvc.delete("/api/books/$notExistId") {
                    contentType = MediaType.APPLICATION_JSON
                }.andExpect {
                    status { isNotFound() }
                }
            }
        }
    }
})