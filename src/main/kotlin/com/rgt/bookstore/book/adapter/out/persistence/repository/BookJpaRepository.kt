package com.rgt.bookstore.book.adapter.out.persistence.repository

import com.rgt.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookJpaRepository : JpaRepository<BookJpaEntity, String>, QuerydslBookRepository {
    fun existsByTitleAndAuthor(title: String, author: String): Boolean
    fun existsByTitleAndAuthorAndIdNot(title: String, author: String, id: String): Boolean
}