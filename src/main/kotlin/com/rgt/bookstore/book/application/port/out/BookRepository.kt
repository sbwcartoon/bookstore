package com.rgt.bookstore.book.application.port.out

import com.rgt.bookstore.book.application.port.`in`.command.SearchFilterCommand
import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.BookId
import com.rgt.bookstore.book.domain.vo.Title
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookRepository {
    fun save(book: Book): Book
    fun existsByTitleAndAuthor(title: Title, author: Author): Boolean
    fun findById(id: BookId): Book?
    fun findAllByCondition(command: SearchFilterCommand, pageable: Pageable): Page<Book>
    fun existsByTitleAndAuthorAndIdNot(title: Title, author: Author, id: BookId): Boolean
}