package com.rgt.bookstore.book.adapter.out.persistence.repository

import com.rgt.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import com.rgt.bookstore.book.application.port.`in`.command.SearchFilterCommand
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QuerydslBookRepository {
    fun findAllByCondition(command: SearchFilterCommand, pageable: Pageable): Page<BookJpaEntity>
}