package com.rgt.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookRequest
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookResponse
import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.integration.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.post

@IntegrationTest
@AutoConfigureMockMvc
class DeleteBookSucceedTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    beforeSpec {
        jpaRepository.deleteAll()
    }

    Given("기존에 존재하는 책 삭제 요청을 할 때") {
        val existing = CreateBookRequest(
            title = "title",
            author = "author",
            price = 1,
            quantity = 1,
        )

        val createResponseString = mockMvc.post("/api/books") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(existing)
        }.andExpect {
            status { isOk() }
        }.andReturn().response.contentAsString

        val existingDto = objectMapper.readValue(createResponseString, CreateBookResponse::class.java)

        When("조건을 만족하면") {
            Then("삭제된다") {
                mockMvc.delete("/api/books/${existingDto.id}") {}.andExpect {
                    status { isOk() }
                }
            }
        }
    }
})