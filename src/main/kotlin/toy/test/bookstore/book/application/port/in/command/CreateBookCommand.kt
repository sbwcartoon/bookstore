package toy.test.bookstore.book.application.port.`in`.command

import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.Price
import toy.test.bookstore.book.unit.domain.vo.Quantity
import toy.test.bookstore.book.unit.domain.vo.Title

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