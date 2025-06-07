package com.rgt.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.rgt.bookstore.book.adapter.`in`.dto.BookDetail
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookRequest
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookResponse
import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.integration.config.IntegrationTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@IntegrationTest
@AutoConfigureMockMvc
class GetBookSucceedTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    beforeSpec {
        jpaRepository.deleteAll()
    }

    Given("책이 존재할 때") {

        val existing = CreateBookRequest(
            title = "title",
            author = "author",
            price = 1,
            quantity = 1,
        )
        val savedString = mockMvc.post("/api/books") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(existing)
        }.andExpect {
            status { isOk() }
        }.andReturn().response.contentAsString

        val savedDto = objectMapper.readValue(savedString, CreateBookResponse::class.java)
        val savedId = savedDto.id

        When("존재하는 아이디로 조회하면") {
            Then("책 상세 정보가 반환된다") {
                val responseString = mockMvc.get("/api/books/$savedId") {
                    contentType = MediaType.APPLICATION_JSON
                }.andExpect {
                    status { isOk() }
                }.andReturn().response.contentAsString

                val responseDto = objectMapper.readValue(responseString, BookDetail::class.java)
                responseDto.id shouldBe savedId
                responseDto.title shouldBe existing.title
                responseDto.author shouldBe existing.author
                responseDto.price shouldBe existing.price
                responseDto.quantity shouldBe existing.quantity
            }
        }
    }
})