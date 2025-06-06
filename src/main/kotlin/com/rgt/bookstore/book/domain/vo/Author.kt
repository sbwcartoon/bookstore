package com.rgt.bookstore.book.domain.vo

import com.rgt.bookstore.book.domain.exception.BlankValueException

@JvmInline
value class Author(val value: String) {
    init {
        if (value.isBlank()) {
            throw BlankValueException()
        }
    }

    override fun toString(): String = value
}