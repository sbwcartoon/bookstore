package toy.test.bookstore.book.unit

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import toy.test.bookstore.book.application.exception.BookNotFoundException
import toy.test.bookstore.book.application.exception.DuplicateBookException
import toy.test.bookstore.book.application.port.`in`.command.UpdateBookCommand
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.application.service.UpdateBookService
import toy.test.bookstore.book.unit.testfixture.BookTestFixture
import java.util.*

class UpdateBookServiceTest : BehaviorSpec({
    val repository = mockk<BookRepository>()
    val service = UpdateBookService(repository)

    Given("정보를 수정하려는 책이 존재하지 않을 때") {
        every { repository.findById(any()) } returns null

        When("수정하면") {
            val notExistId = UUID.randomUUID()
            val book = BookTestFixture.generateBook(id = notExistId)
            val command = UpdateBookCommand(
                id = book.id!!,
                title = book.title,
                author = book.author,
                price = book.price,
                quantity = book.quantity,
            )

            then("오류가 발생한다") {
                shouldThrow<BookNotFoundException> {
                    service.execute(command)
                }
            }
        }
    }

    Given("동일한 제목 및 저자 이름으로 저장된 책이 존재할 때") {
        every { repository.findById(any()) } returns BookTestFixture.generateBook()
        every { repository.existsByTitleAndAuthorAndIdNot(any(), any(), any()) } returns true

        When("수정하면") {
            then("오류가 발생한다") {
                val book = BookTestFixture.generateBook()
                val command = UpdateBookCommand(
                    id = book.id!!,
                    title = book.title,
                    author = book.author,
                    price = book.price,
                    quantity = book.quantity,
                )

                shouldThrow<DuplicateBookException> {
                    service.execute(command)
                }
            }
        }
    }
})