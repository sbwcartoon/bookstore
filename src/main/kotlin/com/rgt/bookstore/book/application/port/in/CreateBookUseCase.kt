package com.rgt.bookstore.book.application.port.`in`

import com.rgt.bookstore.book.application.port.`in`.command.CreateBookCommand
import com.rgt.bookstore.book.domain.model.Book

interface CreateBookUseCase {
    fun execute(command: CreateBookCommand): Book
}