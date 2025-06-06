package com.rgt.bookstore.book.adapter.`in`.dto

import com.rgt.bookstore.book.domain.model.Book

data class BookDetail(
    val id: String,
    val title: String,
    val author: String,
    val price: Int,
    val quantity: Int,
) {
    companion object {
        fun from(book: Book): BookDetail {
            requireNotNull(book.id) { "Saved book must have an id" }.toString()
            return BookDetail(
                id = book.id.toString(),
                title = book.title.toString(),
                author = book.author.toString(),
                price = book.price.value,
                quantity = book.quantity.value,
            )
        }
    }
}