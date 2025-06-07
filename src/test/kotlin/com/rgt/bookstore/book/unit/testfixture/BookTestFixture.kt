package com.rgt.bookstore.book.unit.testfixture

import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.*
import java.util.*

class BookTestFixture {
    companion object {
        fun generateBook(
            id: UUID = UUID.randomUUID(),
            title: String = "Book",
            author: String = "Author",
            price: Int = 1_000,
            quantity: Int = 10,
        ): Book {
            return Book(
                id = BookId(id),
                title = Title(title),
                author = Author(author),
                price = Price(price),
                quantity = Quantity(quantity),
            )
        }
    }
}