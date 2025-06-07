package com.rgt.bookstore.book.adapter.`in`.web

import com.rgt.bookstore.book.adapter.`in`.dto.*
import com.rgt.bookstore.book.application.port.`in`.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val createBookUseCase: CreateBookUseCase,
    private val getBookUseCase: GetBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
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

    @GetMapping
    fun getBooks(
        searchFilterRequest: SearchFilterRequest,
        @PageableDefault(size = 10) pageable: Pageable,
    ): PageResponse<BookSummary> {
        val books = getBooksUseCase.execute(searchFilterRequest.toCommand(), pageable)
        val bookSummaries = books.map { BookSummary.from(it) }
        return PageResponse.from(bookSummaries)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody updateBookRequest: UpdateBookRequest) {
        updateBookUseCase.execute(updateBookRequest.toCommand())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        deleteBookUseCase.execute(id)
    }
}