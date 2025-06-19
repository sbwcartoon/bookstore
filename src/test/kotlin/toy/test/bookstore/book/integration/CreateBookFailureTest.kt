package toy.test.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import toy.test.bookstore.book.adapter.`in`.dto.CreateBookRequest
import toy.test.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import toy.test.bookstore.book.integration.config.IntegrationTest

@IntegrationTest
@AutoConfigureMockMvc
class CreateBookFailureTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    Given("책 생성 요청을 할 때") {

        beforeContainer {
            jpaRepository.deleteAll()
        }

        When("필수정보(제목)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = mapOf(
                    "author" to "author",
                    "price" to 1,
                    "quantity" to 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(저자 이름)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = mapOf(
                    "title" to "title",
                    "price" to 1,
                    "quantity" to 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(가격)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = mapOf(
                    "title" to "title",
                    "author" to "author",
                    "quantity" to 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("필수정보(수량)가 없으면") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = mapOf(
                    "title" to "title",
                    "author" to "author",
                    "price" to 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(제목이 비었으면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = CreateBookRequest(
                    title = "",
                    author = "author",
                    price = 1,
                    quantity = 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(저자 이름이 비었으면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = CreateBookRequest(
                    title = "title",
                    author = "",
                    price = 1,
                    quantity = 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(가격이 음수면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = CreateBookRequest(
                    title = "title",
                    author = "author",
                    price = -100,
                    quantity = 1,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("값이 유효하지 않으면(수량이 음수면)") {
            Then("400 Bad Request 오류가 발생한다") {
                val body = CreateBookRequest(
                    title = "title",
                    author = "author",
                    price = 1,
                    quantity = -100,
                )
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isBadRequest() }
                }
            }
        }

        When("동일한 제목 및 저자 이름으로 저장된 책이 존재하면") {
            val existing = CreateBookRequest(
                title = "title",
                author = "author",
                price = 1,
                quantity = 1,
            )
            mockMvc.post("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(existing)
            }.andExpect {
                status { isOk() }
            }

            Then("409 Conflict 오류가 발생한다") {
                mockMvc.post("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(existing)
                }.andExpect {
                    status { isConflict() }
                }
            }
        }
    }
})