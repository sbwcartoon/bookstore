package toy.test.bookstore.book.application.service

import toy.test.bookstore.book.application.port.`in`.GetBooksUseCase
import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.unit.domain.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetBooksService(
    private val bookRepository: BookRepository,
) : GetBooksUseCase {

    override fun execute(command: SearchFilterCommand, pageable: Pageable): Page<Book> {
        return bookRepository.findAllByCondition(command, pageable)
    }
}