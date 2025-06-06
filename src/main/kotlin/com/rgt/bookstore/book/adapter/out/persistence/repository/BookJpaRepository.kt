package com.rgt.bookstore.book.adapter.out.persistence.repository

import com.rgt.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookJpaRepository : JpaRepository<BookJpaEntity, String> {
    fun existsByTitleAndAuthor(title: String, author: String): Boolean
}