package toy.test.bookstore.book.adapter.`in`.dto

import toy.test.bookstore.book.unit.domain.model.Book

data class CreateBookResponse(val id: String) {
    companion object {
        fun from(book: Book) = CreateBookResponse(
            requireNotNull(book.id) { "Saved book must have an id" }.toString()
        )
    }
}