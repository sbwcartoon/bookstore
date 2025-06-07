package com.rgt.bookstore.book.application.service

import com.rgt.bookstore.book.application.exception.BookNotFoundException
import com.rgt.bookstore.book.application.port.`in`.DeleteBookUseCase
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.domain.vo.BookId
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