package com.rgt.bookstore.book.application.service

import com.rgt.bookstore.book.application.exception.DuplicateBookException
import com.rgt.bookstore.book.application.port.`in`.CreateBookUseCase
import com.rgt.bookstore.book.application.port.`in`.command.CreateBookCommand
import com.rgt.bookstore.book.application.port.out.BookRepository
import com.rgt.bookstore.book.domain.model.Book
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateBookService(
    private val repository: BookRepository,
) : CreateBookUseCase {

    @Transactional
    override fun execute(command: CreateBookCommand): Book {
        val title = command.title
        val author = command.author
        if (repository.existsByTitleAndAuthor(title, author)) {
            throw DuplicateBookException(title, author)
        }

        return repository.save(command.toDomain())
    }
}