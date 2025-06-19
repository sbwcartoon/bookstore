package toy.test.bookstore.book.application.service

import toy.test.bookstore.book.application.exception.BookNotFoundException
import toy.test.bookstore.book.application.port.`in`.GetBookUseCase
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.BookId
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetBookService(
    private val bookRepository: BookRepository,
) : GetBookUseCase {

    override fun execute(id: String): Book {
        val bookId = BookId(UUID.fromString(id))
        return bookRepository.findById(bookId)
            ?: throw BookNotFoundException(bookId)
    }
}