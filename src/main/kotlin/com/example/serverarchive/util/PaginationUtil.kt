package com.example.serverarchive.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.servlet.function.ServerRequest

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

    fun parseParams(req: ServerRequest): Pageable {
        val page = req.param("page").map { it.toIntOrNull() }.orElse(DEFAULT_PAGE)
        val size = req.param("size").map { it.toIntOrNull() }.orElse(DEFAULT_SIZE)
        val sortField = req.param("sortField").orElse(DEFAULT_SORT_FIELD)
        val direction = req.param("direction").map {
            Sort.Direction.valueOf(it.uppercase())
        }.orElse(Sort.Direction.valueOf(DEFAULT_SORT_DIRECTION))

        return createPageable(page, size, sortField, direction)
    }
}
