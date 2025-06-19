package toy.test.bookstore.book.integration

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import toy.test.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import toy.test.bookstore.book.integration.config.IntegrationTest
import java.util.*

@IntegrationTest
@AutoConfigureMockMvc
class GetBookFailureTest(
    private val mockMvc: MockMvc,
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    beforeSpec {
        jpaRepository.deleteAll()
    }

    Given("책이 존재하지 않을 때") {
        When("존재하지 않는 아이디로 조회하면") {
            Then("404 Not Found 오류가 발생한다") {
                val notExistId = UUID.randomUUID()
                mockMvc.get("/api/books/$notExistId") {
                    contentType = MediaType.APPLICATION_JSON
                }.andExpect {
                    status { isNotFound() }
                }
            }
        }
    }
})