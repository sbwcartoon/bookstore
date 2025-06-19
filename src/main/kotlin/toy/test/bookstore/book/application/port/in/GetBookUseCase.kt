package toy.test.bookstore.book.application.port.`in`

import toy.test.bookstore.book.unit.domain.model.Book

interface GetBookUseCase {
    fun execute(id: String): Book
}