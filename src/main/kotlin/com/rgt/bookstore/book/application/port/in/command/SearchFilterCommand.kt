package com.rgt.bookstore.book.application.port.`in`.command

import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Title

data class SearchFilterCommand(
    val title: Title? = null,
    val author: Author? = null,
)