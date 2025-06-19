package toy.test.bookstore.book.adapter.`in`.dto

import org.springframework.data.domain.Page

data class PageResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val totalElements: Long,
    val isFirst: Boolean,
    val isLast: Boolean
) {
    companion object {
        fun <T> from(page: Page<T>): PageResponse<T> {
            return PageResponse(
                content = page.content,
                page = page.number + 1,
                size = page.size,
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                isFirst = page.isFirst,
                isLast = page.isLast
            )
        }
    }
}