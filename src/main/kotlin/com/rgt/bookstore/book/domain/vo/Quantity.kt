package com.rgt.bookstore.book.domain.vo

import com.rgt.bookstore.book.domain.exception.NegativeValueException

@JvmInline
value class Quantity(val value: Int) {
    init {
        if (value < 0) {
            throw NegativeValueException()
        }
    }

    override fun toString(): String = value.toString()
}