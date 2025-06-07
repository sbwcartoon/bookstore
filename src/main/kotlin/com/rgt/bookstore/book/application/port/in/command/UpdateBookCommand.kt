package com.rgt.bookstore.book.application.port.`in`.command

import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.*

data class UpdateBookCommand(
    val id: BookId,
    val title: Title,
    val author: Author,
    val price: Price,
    val quantity: Quantity,
) {
    fun toDomain() = Book(
        id = id,
        title = title,
        author = author,
        price = price,
        quantity = quantity,
    )
}