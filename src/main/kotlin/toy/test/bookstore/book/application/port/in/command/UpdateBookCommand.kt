package toy.test.bookstore.book.application.port.`in`.command

import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.*

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