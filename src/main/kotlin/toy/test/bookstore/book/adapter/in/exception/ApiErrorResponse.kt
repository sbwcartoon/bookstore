package toy.test.bookstore.book.adapter.`in`.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class ApiErrorResponse(
    val timestamp: String = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
    val status: Int,
    val error: String,
    val message: String,
    val path: String
) {
    companion object {
        fun build(status: HttpStatus, message: String, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {
            val errorResponse = ApiErrorResponse(
                status = status.value(),
                error = status.reasonPhrase,
                message = message,
                path = request.requestURI
            )
            return ResponseEntity.status(status).body(errorResponse)
        }
    }
}