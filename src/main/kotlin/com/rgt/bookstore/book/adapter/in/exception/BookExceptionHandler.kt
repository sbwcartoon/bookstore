package com.rgt.bookstore.book.adapter.`in`.exception

import com.rgt.bookstore.book.application.exception.BookNotFoundException
import com.rgt.bookstore.book.application.exception.DuplicateBookException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class BookExceptionHandler(
    private val request: HttpServletRequest
) {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFoundException(e: Exception): ResponseEntity<ApiErrorResponse> {
        return ApiErrorResponse.build(
            status = HttpStatus.NOT_FOUND,
            message = e.javaClass.simpleName,
            request = request,
        )
    }

    @ExceptionHandler(DuplicateBookException::class)
    fun handleDuplicateBookException(e: Exception): ResponseEntity<ApiErrorResponse> {
        return ApiErrorResponse.build(
            status = HttpStatus.CONFLICT,
            message = e.javaClass.simpleName,
            request = request,
        )
    }
}
