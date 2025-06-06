package com.rgt.bookstore.book.application.port.`in`.command

import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Price
import com.rgt.bookstore.book.domain.vo.Quantity
import com.rgt.bookstore.book.domain.vo.Title

data class CreateBookCommand(
    val title: Title,
    val author: Author,
    val price: Price,
    val quantity: Quantity,
) {
    fun toDomain() = Book(
        title = title,
        author = author,
        price = price,
        quantity = quantity,
    )
}