package toy.test.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import toy.test.bookstore.book.adapter.`in`.dto.CreateBookRequest
import toy.test.bookstore.book.adapter.`in`.dto.CreateBookResponse
import toy.test.bookstore.book.adapter.`in`.dto.UpdateBookRequest
import toy.test.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import toy.test.bookstore.book.integration.config.IntegrationTest
import toy.test.bookstore.book.unit.domain.vo.BookId
import java.util.*

@IntegrationTest
@AutoConfigureMockMvc
class UpdateBookFailureTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    Given("책 정보 수정 요청을 할 때") {

        beforeContainer {
            jpaRepository.deleteAll()
        }

        When("필수정보(제목)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = mapOf(
                    "id" to id,
                    "author" to "author",
                    "price" to 1,
                    "quantity" to 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(저자 이름)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = mapOf(
                    "id" to id,
                    "title" to "title",
                    "price" to 1,
                    "quantity" to 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(가격)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = mapOf(
                    "id" to id,
                    "title" to "title",
                    "author" to "author",
                    "quantity" to 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(수량)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = mapOf(
                    "id" to id,
                    "title" to "title",
                    "author" to "author",
                    "price" to 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(제목이 비었으면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = UpdateBookRequest(
                    id = id,
                    title = "",
                    author = "author",
                    price = 1,
                    quantity = 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(저자 이름이 비었으면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = UpdateBookRequest(
                    id = id,
                    title = "title",
                    author = "",
                    price = 1,
                    quantity = 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(가격이 음수면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = UpdateBookRequest(
                    id = id,
                    title = "title",
                    author = "author",
                    price = -100,
                    quantity = 1,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(수량이 음수면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val id = BookId.Companion.generate().toString()
                val body = UpdateBookRequest(
                    id = id,
                    title = "title",
                    author = "author",
                    price = 1,
                    quantity = -100,
                )
                mockMvc.put("/api/books/${id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("수정하려는 것과 동일한 제목 및 저자 이름으로 저장된 책이 존재하면") {
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

            val duplicated = CreateBookRequest(
                title = "중복될 title",
                author = "중복될 author",
                price = 1,
                quantity = 1,
            )
            mockMvc.post("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(duplicated)
            }.andExpect {
                status { isOk() }
            }

            Then("409 Conflict 오류가 발생한다") {
                val updateRequest = UpdateBookRequest(
                    id = existingDto.id,
                    title = duplicated.title,
                    author = duplicated.author,
                    price = duplicated.price!! - 1,
                    quantity = duplicated.quantity!! - 1,
                )

                mockMvc.put("/api/books/${existingDto.id}") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(updateRequest)
                }.andExpect {
                    status { isConflict() }
                }
            }
        }


        When("존재하지 책을 수정하면") {
            Then("404 Not Found 오류가 발생한다") {
                val notExistId = UUID.randomUUID().toString()
                val body = UpdateBookRequest(
                    id = notExistId,
                    title = "title",
                    author = "author",
                    price = 1,
                    quantity = 1,
                )
                mockMvc.put("/api/books/$notExistId") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isNotFound() }
                }
            }
        }
    }
})