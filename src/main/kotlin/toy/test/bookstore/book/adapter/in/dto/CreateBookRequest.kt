package toy.test.bookstore.book.adapter.`in`.dto

import toy.test.bookstore.book.application.port.`in`.command.CreateBookCommand
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.Price
import toy.test.bookstore.book.unit.domain.vo.Quantity
import toy.test.bookstore.book.unit.domain.vo.Title

data class CreateBookRequest(
    val title: String,
    val author: String,
    val price: Int?,
    val quantity: Int?,
) {
    fun toCommand(): CreateBookCommand {
        requireNotNull(price)
        requireNotNull(quantity)

        return CreateBookCommand(
            title = Title(title),
            author = Author(author),
            price = Price(price),
            quantity = Quantity(quantity),
        )
    }
}