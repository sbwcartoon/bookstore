package com.rgt.bookstore.book.application.port.`in`

import com.rgt.bookstore.book.application.port.`in`.command.SearchFilterCommand
import com.rgt.bookstore.book.domain.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetBooksUseCase {
    fun execute(command: SearchFilterCommand, pageable: Pageable): Page<Book>
}