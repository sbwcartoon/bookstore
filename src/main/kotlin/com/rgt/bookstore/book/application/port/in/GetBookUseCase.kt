package com.rgt.bookstore.book.application.port.`in`

import com.rgt.bookstore.book.domain.model.Book

interface GetBookUseCase {
    fun execute(id: String): Book
}