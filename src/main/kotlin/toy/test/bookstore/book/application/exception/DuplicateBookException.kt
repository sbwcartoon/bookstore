package toy.test.bookstore.book.application.exception

import toy.test.bookstore.book.unit.domain.vo.Author
import toy.test.bookstore.book.unit.domain.vo.Title

class DuplicateBookException(
    title: Title,
    author: Author,
) : IllegalArgumentException("Title '$title' and Author '$author' already exist")