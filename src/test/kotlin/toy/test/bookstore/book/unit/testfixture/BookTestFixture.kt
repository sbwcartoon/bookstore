package toy.test.bookstore.book.unit.testfixture

import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.*
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