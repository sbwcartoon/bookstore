package com.rgt.bookstore.book.integration.util

object PaginationTestUtil {

    fun <T> paginateByZeroIndexed(
        list: List<T>,
        page: Int,
        size: Int,
    ): List<T> {
        return paginate(list, page, size)
    }

    fun <T> paginateByOneIndexed(
        list: List<T>,
        page: Int,
        size: Int,
    ): List<T> {
        return paginate(list, page, size, false)
    }

    private fun <T> paginate(
        list: List<T>,
        page: Int,
        size: Int,
        isZeroIndexed: Boolean = true,
    ): List<T> {
        val fromIndex = (page - if (isZeroIndexed) 0 else 1) * size
        if (fromIndex >= list.size) {
            return emptyList()
        }

        val toIndex = minOf(fromIndex + size, list.size)
        return list.subList(fromIndex, toIndex)
    }
}