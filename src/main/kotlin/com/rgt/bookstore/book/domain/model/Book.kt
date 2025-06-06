package com.rgt.bookstore.book.domain.model

import com.rgt.bookstore.book.domain.vo.*

data class Book(
    val id: BookId,
    val title: Title,
    val author: Author,
    val price: Price,
    val quantity: Quantity,
)