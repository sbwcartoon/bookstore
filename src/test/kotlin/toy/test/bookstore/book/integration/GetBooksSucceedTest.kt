package toy.test.bookstore.book.integration

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import toy.test.bookstore.book.adapter.`in`.dto.CreateBookRequest
import toy.test.bookstore.book.adapter.`in`.dto.PageResponse
import toy.test.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import toy.test.bookstore.book.integration.config.IntegrationTest
import toy.test.bookstore.book.integration.util.PaginationTestUtil

@IntegrationTest
@AutoConfigureMockMvc
class GetBooksSucceedTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    beforeSpec {
        jpaRepository.deleteAll()
    }

    val defaultPage = 1
    val defaultSize = 10

    Given("책이 존재할 때") {
        val existingBooks = listOf(
            CreateBookRequest(title = "title", author = "author1", price = 1, quantity = 1),
            CreateBookRequest(title = "title1", author = "author1", price = 1, quantity = 1),
            CreateBookRequest(title = "title1", author = "author", price = 1, quantity = 1),
        )

        for (body in existingBooks) {
            mockMvc.post("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(body)
            }.andExpect {
                status { isOk() }
            }
        }

        When("조건 없이 조회하면") {
            val responseString = mockMvc.get("/api/books") {}.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(existingBooks),
                page = defaultPage,
                size = defaultSize,
            )

            Then("페이지 정보는 기본값으로 설정된다") {
                responseDto.page shouldBe defaultPage
                responseDto.size shouldBe defaultSize
            }

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, existingBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }

        When("유효한 페이지 정보를 전달하면") {
            val page = 1
            val size = 2
            val responseString = mockMvc.get("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                param("page", page.toString())
                param("size", size.toString())
            }.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(existingBooks),
                page = page,
                size = size,
            )

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, existingBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }

        When("제목 필터를 전달하면") {
            val title = "title1"
            val responseString = mockMvc.get("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                param("title", title)
            }.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val filteredBooks = existingBooks.filter { it.title == title }

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(filteredBooks),
                page = defaultPage,
                size = defaultSize,
            )

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, filteredBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }

        When("저자 필터를 전달하면") {
            val author = "author1"
            val responseString = mockMvc.get("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                param("author", author)
            }.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val filteredBooks = existingBooks.filter { it.author == author }

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(filteredBooks),
                page = defaultPage,
                size = defaultSize,
            )

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, filteredBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }

        When("제목, 저자 필터를 전달하면") {
            val title = "title1"
            val author = "author1"
            val responseString = mockMvc.get("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                param("title", title)
                param("author", author)
            }.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val filteredBooks = existingBooks.filter { it.title == title && it.author == author }

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(filteredBooks),
                page = defaultPage,
                size = defaultSize,
            )

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, filteredBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }

        When("페이지 정보, 제목, 저자 필터를 전달하면") {
            val page = 1
            val size = 1
            val title = "title1"
            val author = "author1"
            val responseString = mockMvc.get("/api/books") {
                contentType = MediaType.APPLICATION_JSON
                param("page", page.toString())
                param("size", size.toString())
                param("title", title)
                param("author", author)
            }.andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

            val filteredBooks = existingBooks.filter { it.title == title && it.author == author }

            val booksPaginated = PaginationTestUtil.paginateByOneIndexed(
                list = sortedByTitleAndAuthor(filteredBooks),
                page = page,
                size = size,
            )

            val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)

            Then("전체 책 수가 정상적으로 조회된다") {
                assertTotalElements(responseDto, filteredBooks)
            }

            Then("조회된 책 수가 정상적으로 조회된다") {
                assertContentSize(responseDto, booksPaginated)
            }

            Then("책 목록이 정상적으로 조회된다") {
                assertContent(responseDto, booksPaginated)
            }
        }
    }

    Given("책 목록을 조회할 때") {

        When("유효하지 않은 페이지 정보를 전달하면(페이지 번호 오류)") {
            Then("기본값으로 변환되고 오류가 발생하지 않는다") {
                val responseString = mockMvc.get("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    param("page", "-9999999999999999999999")
                    param("size", "10")
                }.andExpect {
                    status { isOk() }
                }.andReturn().response.contentAsString

                val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)
                responseDto.page shouldBe 1
            }
        }

        When("유효하지 않은 페이지 정보를 전달하면(페이지별 항목 수 오류)") {
            Then("기본값으로 변환되고 오류가 발생하지 않는다") {
                val responseString = mockMvc.get("/api/books") {
                    contentType = MediaType.APPLICATION_JSON
                    param("page", "1")
                    param("size", "-9999999999999999999999")
                }.andExpect {
                    status { isOk() }
                }.andReturn().response.contentAsString

                val responseDto = objectMapper.readValue(responseString, PageResponse::class.java)
                responseDto.size shouldBe 10
            }
        }
    }
}) {
    companion object {
        fun assertTotalElements(
            responseDto: PageResponse<*>,
            shouldBeList: List<CreateBookRequest>,
        ) = responseDto.totalElements shouldBe shouldBeList.size

        fun assertContentSize(
            responseDto: PageResponse<*>,
            shouldBeList: List<CreateBookRequest>,
        ) = responseDto.content.size shouldBe shouldBeList.size

        fun assertContent(
            responseDto: PageResponse<*>,
            shouldBeList: List<CreateBookRequest>,
        ) {
            val requestContents = shouldBeList.map {
                mapOf(
                    "title" to it.title,
                    "author" to it.author,
                    "quantity" to it.quantity,
                )
            }

            val responseContents = responseDto.content.map {
                mapOf(
                    "title" to (it as Map<*, *>)["title"],
                    "author" to it["author"] as String,
                    "quantity" to it["quantity"] as Int?,
                )
            }

            val requestJsons = requestContents.map { ObjectMapper().writeValueAsString(it) }
            val responseJsons = responseContents.map { ObjectMapper().writeValueAsString(it) }

            requestJsons shouldContainExactly responseJsons
        }


        fun sortedByTitleAndAuthor(list: List<CreateBookRequest>): List<CreateBookRequest> {
            return list.sortedWith(compareBy(CreateBookRequest::title, CreateBookRequest::author))
        }
    }
}