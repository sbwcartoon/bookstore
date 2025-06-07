package com.rgt.bookstore.book.adapter.`in`.dto

import com.rgt.bookstore.book.application.port.`in`.command.UpdateBookCommand
import com.rgt.bookstore.book.domain.vo.*
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