package com.rgt.bookstore.book.adapter.out.persistence

import com.rgt.bookstore.book.adapter.out.persistence.mapper.BookJpaMapper
import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Title
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
}