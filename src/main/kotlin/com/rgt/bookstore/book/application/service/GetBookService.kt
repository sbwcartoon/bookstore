package com.rgt.bookstore.book.application.service

import com.rgt.bookstore.book.application.exception.BookNotFoundException
import com.rgt.bookstore.book.application.port.`in`.GetBookUseCase
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.BookId
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