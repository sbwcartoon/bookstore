package toy.test.bookstore.book.unit.domain.vo

import toy.test.bookstore.book.unit.domain.exception.NegativeValueException

@JvmInline
value class Quantity(val value: Int) {
    init {
        if (value < 0) {
            throw NegativeValueException()
        }
    }

    override fun toString(): String = value.toString()
}