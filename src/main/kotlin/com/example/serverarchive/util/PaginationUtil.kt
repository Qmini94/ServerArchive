package com.example.serverarchive.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

object PaginationUtil {
    private const val DEFAULT_PAGE = 1
    private const val DEFAULT_SIZE = 10
    private const val DEFAULT_SORT_FIELD = "idx"
    private const val DEFAULT_SORT_DIRECTION = "DESC"

    private fun createPageable(
        page: Int? = null,
        size: Int? = null,
        sortField: String = DEFAULT_SORT_FIELD,
        direction: Sort.Direction = Sort.Direction.DESC
    ): Pageable {
        val pageNum = page?.takeIf { it > 0 } ?: DEFAULT_PAGE
        val pageSize = size?.takeIf { it > 0 } ?: DEFAULT_SIZE
        return PageRequest.of(pageNum - 1, pageSize, Sort.by(direction, sortField))
    }

    fun parseParams(req: HttpServletRequest): Pageable {
        val page = req.getParameter("page")?.toIntOrNull() ?: DEFAULT_PAGE
        val size = req.getParameter("size")?.toIntOrNull() ?: DEFAULT_SIZE
        val sortField = req.getParameter("sortField") ?: DEFAULT_SORT_FIELD
        val direction = req.getParameter("direction")?.let {
            Sort.Direction.valueOf(it.uppercase())
        } ?: Sort.Direction.valueOf(DEFAULT_SORT_DIRECTION)

        return createPageable(page, size, sortField, direction)
    }
}
