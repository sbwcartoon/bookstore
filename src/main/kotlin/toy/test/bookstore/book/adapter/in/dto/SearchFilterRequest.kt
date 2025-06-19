package toy.test.bookstore.book.adapter.`in`.dto

import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.Title

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