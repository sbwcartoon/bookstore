package com.rgt.bookstore.book.adapter.out.persistence.mapper

import com.rgt.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import com.rgt.bookstore.book.domain.model.Book
import com.rgt.bookstore.book.domain.vo.*
import java.util.*

object BookJpaMapper {
    fun toEntity(book: Book): BookJpaEntity {
        return BookJpaEntity(
            id = book.id?.toString() ?: BookId.generate().toString(),
            title = book.title.value,
            author = book.author.value,
            price = book.price.value,
            quantity = book.quantity.value,
        )
    }

    fun toDomain(entity: BookJpaEntity): Book {
        return Book(
            id = BookId(UUID.fromString(entity.id)),
            title = Title(entity.title),
            author = Author(entity.author),
            price = Price(entity.price),
            quantity = Quantity(entity.quantity),
        )
    }
}