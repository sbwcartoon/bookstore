package toy.test.bookstore.book.unit.domain.model

import toy.test.bookstore.book.unit.domain.vo.*

data class Book(
    val title: Title,
    val author: Author,
    val price: Price,
    val quantity: Quantity,
    val id: BookId? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        return this.title == other.title && this.author == other.author
    }

    override fun hashCode(): Int {
        return 31 * title.hashCode() + author.hashCode()
    }
}