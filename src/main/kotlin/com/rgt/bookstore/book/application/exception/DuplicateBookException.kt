package com.rgt.bookstore.book.application.exception

import com.rgt.bookstore.book.domain.vo.Author
import com.rgt.bookstore.book.domain.vo.Title

class DuplicateBookException(
    title: Title,
    author: Author,
) : IllegalArgumentException("Title '$title' and Author '$author' already exist")