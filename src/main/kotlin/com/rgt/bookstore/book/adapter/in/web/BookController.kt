package com.rgt.bookstore.book.adapter.`in`.web

import com.rgt.bookstore.book.adapter.`in`.dto.BookDetail
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookRequest
import com.rgt.bookstore.book.adapter.`in`.dto.CreateBookResponse
import com.rgt.bookstore.book.application.port.`in`.CreateBookUseCase
import com.rgt.bookstore.book.application.port.`in`.GetBookUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val createBookUseCase: CreateBookUseCase,
    private val getBookUseCase: GetBookUseCase,
) {

    @PostMapping
    fun create(@RequestBody createBookRequest: CreateBookRequest): CreateBookResponse {
        val createdBook = createBookUseCase.execute(createBookRequest.toCommand())
        return CreateBookResponse.from(createdBook)
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: String): BookDetail {
        val book = getBookUseCase.execute(id)
        return BookDetail.from(book)
    }
}