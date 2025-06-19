package toy.test.bookstore.book.application.port.`in`

import toy.test.bookstore.book.application.port.`in`.command.CreateBookCommand
import toy.test.bookstore.book.unit.domain.model.Book

interface CreateBookUseCase {
    fun execute(command: CreateBookCommand): Book
}