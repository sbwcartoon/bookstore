package com.rgt.bookstore.book.adapter.`in`.dto

import com.rgt.bookstore.book.domain.model.Book

data class CreateBookResponse(val id: String) {
    companion object {
        fun from(book: Book) = CreateBookResponse(
            requireNotNull(book.id) { "Saved book must have an id" }.toString()
        )
    }
}