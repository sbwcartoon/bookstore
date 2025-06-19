package toy.test.bookstore.book.application.port.`in`

import toy.test.bookstore.book.application.port.`in`.command.UpdateBookCommand

interface UpdateBookUseCase {
    fun execute(command: UpdateBookCommand)
}