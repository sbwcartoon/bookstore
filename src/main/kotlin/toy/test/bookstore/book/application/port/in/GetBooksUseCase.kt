package toy.test.bookstore.book.application.port.`in`

import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import toy.test.bookstore.book.unit.domain.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetBooksUseCase {
    fun execute(command: SearchFilterCommand, pageable: Pageable): Page<Book>
}