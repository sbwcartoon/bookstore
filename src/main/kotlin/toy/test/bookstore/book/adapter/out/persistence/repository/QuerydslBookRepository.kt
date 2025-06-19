package toy.test.bookstore.book.adapter.out.persistence.repository

import toy.test.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QuerydslBookRepository {
    fun findAllByCondition(command: SearchFilterCommand, pageable: Pageable): Page<BookJpaEntity>
}