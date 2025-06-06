package com.rgt.bookstore.book.adapter.`in`.web

import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookRequest
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookResponse
import com.rgt.bookstore.book.application.port.`in`.CreateBookUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(private val createBookUseCase: CreateBookUseCase) {

    @PostMapping
    fun create(@RequestBody createBookRequest: CreateBookRequest): CreateBookResponse {
        val createdBook = createBookUseCase.execute(createBookRequest.toCommand())
        return CreateBookResponse.from(createdBook)
    }
}