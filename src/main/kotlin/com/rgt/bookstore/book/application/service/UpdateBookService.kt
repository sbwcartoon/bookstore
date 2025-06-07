package com.rgt.bookstore.book.application.service

import com.rgt.bookstore.book.application.exception.BookNotFoundException
import com.rgt.bookstore.book.application.exception.DuplicateBookException
import com.rgt.bookstore.book.application.port.`in`.UpdateBookUseCase
import com.rgt.bookstore.book.application.port.`in`.command.UpdateBookCommand
import com.rgt.bookstore.book.application.port.out.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateBookService(
    private val repository: BookRepository,
) : UpdateBookUseCase {

    @Transactional
    override fun execute(command: UpdateBookCommand) {
        repository.findById(command.id)
            ?: throw BookNotFoundException(command.id)

        val title = command.title
        val author = command.author
        if (repository.existsByTitleAndAuthorAndIdNot(title, author, command.id)) {
            throw DuplicateBookException(title, author)
        }

        repository.save(command.toDomain())
    }
}