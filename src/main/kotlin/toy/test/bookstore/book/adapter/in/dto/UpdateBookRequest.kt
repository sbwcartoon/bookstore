package toy.test.bookstore.book.adapter.`in`.dto

import toy.test.bookstore.book.application.port.`in`.command.UpdateBookCommand
import toy.test.bookstore.book.unit.domain.vo.*
import java.util.*

data class UpdateBookRequest(
    val id: String,
    val title: String,
    val author: String,
    val price: Int?,
    val quantity: Int?,
) {
    fun toCommand(): UpdateBookCommand {
        requireNotNull(price)
        requireNotNull(quantity)

        return UpdateBookCommand(
            id = BookId(UUID.fromString(id)),
            title = Title(title),
            author = Author(author),
            price = Price(price),
            quantity = Quantity(quantity),
        )
    }
}