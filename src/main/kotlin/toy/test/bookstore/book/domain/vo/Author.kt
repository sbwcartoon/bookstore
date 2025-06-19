package toy.test.bookstore.book.unit.domain.vo

import toy.test.bookstore.book.unit.domain.exception.BlankValueException

@JvmInline
value class Author(val value: String) {
    init {
        if (value.isBlank()) {
            throw BlankValueException()
        }
    }

    override fun toString(): String = value
}