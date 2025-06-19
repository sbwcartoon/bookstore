package toy.test.bookstore.book.adapter.out.persistence.mapper

import toy.test.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import toy.test.bookstore.book.unit.domain.model.Book
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.BookId
import toy.test.bookstore.book.unit.domain.vo.Price
import toy.test.bookstore.book.unit.domain.vo.Quantity
import toy.test.bookstore.book.unit.domain.vo.Title
import java.util.UUID

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