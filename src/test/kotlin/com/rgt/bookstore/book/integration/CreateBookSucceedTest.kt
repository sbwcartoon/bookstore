package com.rgt.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookRequest
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookResponse
import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@IntegrationTest
@AutoConfigureMockMvc
class CreateBookSucceedTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    Given("책 생성 요청을 할 때") {

        beforeContainer {
            jpaRepository.deleteAll()
        }

        When("조건을 만족하면") {
            Then("저장되고 저장한 책 아이디가 반환된다") {
                val existing = CreateBookRequest(
                    title = "title",
                    author = "author",
                    price = 1,
                    quantity = 1,
                )

                val responseString = mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(existing)
                }.andExpect {
                    status { isOk() }
                }.andReturn().response.contentAsString

                val responseDto = objectMapper.readValue(responseString, CreateBookResponse::class.java)
                responseDto.id shouldNotBe ""
            }
        }
    }
})