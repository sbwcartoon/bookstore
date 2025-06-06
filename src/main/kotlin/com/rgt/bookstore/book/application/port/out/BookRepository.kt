package com.rgt.bookstore.book.application.port.out

import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.BookId
import com.rgt.bookstore.book.domain.vo.Title

interface BookRepository {
    fun save(book: Book): Book
    fun existsByTitleAndAuthor(title: Title, author: Author): Boolean
    fun findById(id: BookId): Book?
}