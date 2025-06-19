package toy.test.bookstore.book.unit

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import toy.test.bookstore.book.application.exception.BookNotFoundException
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.application.service.DeleteBookService
import java.util.*

class DeleteBookServiceTest : BehaviorSpec({
    val repository = mockk<BookRepository>()
    val service = DeleteBookService(repository)

    Given("삭제하려는 책이 존재하지 않을 때") {
        every { repository.findById(any()) } returns null

        When("삭제하면") {
            then("오류가 발생한다") {
                val notExistId = UUID.randomUUID().toString()
                shouldThrow<BookNotFoundException> {
                    service.execute(notExistId)
                }
            }
        }
    }
})