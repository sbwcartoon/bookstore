package com.rgt.bookstore.book.adapter.`in`.dto

import com.rgt.bookstore.book.application.port.`in`.command.SearchFilterCommand
import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Title

data class SearchFilterRequest(
    val title: String?,
    val author: String?,
) {
    fun toCommand(): SearchFilterCommand {
        return SearchFilterCommand(
            title = if (title == null) null else Title(title),
            author = if (author == null) null else Author(author),
        )
    }
}