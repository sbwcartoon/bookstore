package com.rgt.bookstore.book.adapter.`in`.dto

import com.rgt.bookstore.book.application.port.`in`.command.CreateBookCommand
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Price
import com.rgt.bookstore.book.domain.vo.Quantity
import com.rgt.bookstore.book.domain.vo.Title

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