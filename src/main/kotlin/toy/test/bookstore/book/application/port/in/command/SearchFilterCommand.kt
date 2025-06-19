package toy.test.bookstore.book.application.port.`in`.command

import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.Title

data class SearchFilterCommand(
    val title: Title? = null,
    val author: Author? = null,
)