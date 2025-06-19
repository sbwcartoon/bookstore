package toy.test.bookstore.book.application.service

import toy.test.bookstore.book.application.exception.BookNotFoundException
import toy.test.bookstore.book.application.port.`in`.DeleteBookUseCase
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.unit.domain.vo.BookId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeleteBookService(
    private val repository: BookRepository,
) : DeleteBookUseCase {

    @Transactional
    override fun execute(id: String) {
        val bookId = BookId(UUID.fromString(id))
        repository.findById(bookId)
            ?: throw BookNotFoundException(bookId)

        repository.deleteById(bookId)
    }
}