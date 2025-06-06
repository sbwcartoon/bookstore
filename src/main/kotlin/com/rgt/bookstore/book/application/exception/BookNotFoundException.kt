package com.rgt.bookstore.book.application.exception

import com.rgt.bookstore.book.domain.vo.BookId

class BookNotFoundException(id: BookId) : NoSuchElementException("Book with id '$id' not found")