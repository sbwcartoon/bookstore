package com.rgt.bookstore.book.application.service

import com.rgt.bookstore.book.application.port.`in`.GetBooksUseCase
import com.rgt.bookstore.book.application.port.`in`.command.SearchFilterCommand
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.domain.model.Book
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