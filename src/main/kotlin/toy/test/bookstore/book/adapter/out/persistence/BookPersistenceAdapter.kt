package toy.test.bookstore.book.adapter.out.persistence

import toy.test.bookstore.book.adapter.out.persistence.mapper.BookJpaMapper
import toy.test.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import toy.test.bookstore.book.application.port.out.BookRepository
import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.BookId
import toy.test.bookstore.book.unit.domain.vo.Title
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class BookPersistenceAdapter(
    private val bookJpaRepository: BookJpaRepository
) : BookRepository {

    override fun save(book: Book): Book {
        val saved = bookJpaRepository.save(BookJpaMapper.toEntity(book))
        return BookJpaMapper.toDomain(saved)
    }

    override fun existsByTitleAndAuthor(title: Title, author: Author): Boolean {
        return bookJpaRepository.existsByTitleAndAuthor(title.toString(), author.toString())
    }

    override fun findById(id: BookId): Book? {
        return bookJpaRepository.findById(id.toString())
            .map { BookJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findAllByCondition(
        command: SearchFilterCommand,
        pageable: Pageable
    ): Page<Book> {
        return bookJpaRepository.findAllByCondition(command, pageable)
            .map { BookJpaMapper.toDomain(it) }
    }

    override fun existsByTitleAndAuthorAndIdNot(
        title: Title,
        author: Author,
        id: BookId
    ): Boolean {
        return bookJpaRepository.existsByTitleAndAuthorAndIdNot(
            title.toString(),
            author.toString(),
            id.toString(),
        )
    }

    override fun deleteById(bookId: BookId) {
        bookJpaRepository.deleteById(bookId.toString())
    }
}