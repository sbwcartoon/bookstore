package com.rgt.bookstore.book.application.port.`in`

import com.rgt.bookstore.book.application.port.`in`.command.UpdateBookCommand

interface UpdateBookUseCase {
    fun execute(command: UpdateBookCommand)
}