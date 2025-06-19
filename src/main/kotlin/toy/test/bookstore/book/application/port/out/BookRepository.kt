package toy.test.bookstore.book.application.port.out

import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.BookId
import toy.test.bookstore.book.unit.domain.vo.Title
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookRepository {
    fun save(book: Book): Book
    fun existsByTitleAndAuthor(title: Title, author: Author): Boolean
    fun findById(id: BookId): Book?
    fun findAllByCondition(command: SearchFilterCommand, pageable: Pageable): Page<Book>
    fun existsByTitleAndAuthorAndIdNot(title: Title, author: Author, id: BookId): Boolean
    fun deleteById(bookId: BookId)
}