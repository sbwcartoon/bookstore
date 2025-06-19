package toy.test.bookstore.book.application.exception

import toy.test.bookstore.book.unit.domain.vo.BookId

class BookNotFoundException(id: BookId) : NoSuchElementException("Book with id '$id' not found")