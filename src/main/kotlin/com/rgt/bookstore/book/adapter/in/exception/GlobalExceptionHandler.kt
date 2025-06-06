package com.rgt.bookstore.book.adapter.`in`.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
class GlobalExceptionHandler(
    private val request: HttpServletRequest
) {

    @ExceptionHandler(
        HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class,
        MethodArgumentTypeMismatchException::class,
        IllegalArgumentException::class,
    )
    fun handleArgumentNotValidException(e: Exception): ResponseEntity<ApiErrorResponse> {
        return ApiErrorResponse.build(
            status = HttpStatus.BAD_REQUEST,
            message = e.javaClass.simpleName,
            request = request,
        )
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingParam(e: MissingServletRequestParameterException): ResponseEntity<ApiErrorResponse> {
        return ApiErrorResponse.build(
            status = HttpStatus.BAD_REQUEST,
            message = "Missing Parameter '${e.parameterName}'",
            request = request,
        )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleServerException(e: Exception): ResponseEntity<ApiErrorResponse> {
        e.printStackTrace()

        return ApiErrorResponse.build(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = e.javaClass.simpleName,
            request = request,
        )
    }
}