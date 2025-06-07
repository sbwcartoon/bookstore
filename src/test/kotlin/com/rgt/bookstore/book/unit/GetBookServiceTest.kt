package com.rgt.bookstore.book.unit

import com.rgt.bookstore.book.application.exception.BookNotFoundException
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.application.service.GetBookService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class GetBookServiceTest : BehaviorSpec({
    val repository = mockk<BookRepository>()
    val service = GetBookService(repository)

    Given("조회하려는 책이 존재하지 않을 때") {
        every { repository.findById(any()) } returns null

        When("조회하면") {
            then("오류가 발생한다") {
                shouldThrow<BookNotFoundException> {
                    val notExistId = UUID.randomUUID()
                    service.execute(notExistId.toString())
                }
            }
        }
    }
})