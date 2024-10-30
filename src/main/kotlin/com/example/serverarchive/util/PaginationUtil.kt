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

    /**
     * 페이지네이션을 위한 Pageable 객체를 생성합니다.
     *
     * @param page 요청된 페이지 번호 (1부터 시작). 기본값은 1입니다.
     * @param size 요청된 페이지 크기. 기본값은 10입니다.
     * @param sortField 정렬할 필드 이름. 기본값은 "id"입니다.
     * @param direction 정렬 방향 (ASC 또는 DESC). 기본값은 DESC입니다.
     * @return Spring Data JPA에서 사용하는 Pageable 객체입니다.
     */
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

    /**
     * 서버 요청에서 페이지네이션 관련 파라미터를 추출하여 Pageable 객체를 생성합니다.
     *
     * @param req 서버로부터 전달된 요청 객체입니다.
     * @return 요청 파라미터에 기반한 Pageable 객체입니다.
     */
    fun parseParams(req: ServerRequest): Pageable {
        val page = req.param("page").map { it.toIntOrNull() }.orElse(DEFAULT_PAGE)
        val size = req.param("size").map { it.toIntOrNull() }.orElse(DEFAULT_SIZE)
        val sortField = req.param("sortField").orElse(DEFAULT_SORT_FIELD)
        val direction = req.param("direction").map {
            Sort.Direction.valueOf(it.uppercase())
        }.orElse(Sort.Direction.valueOf(DEFAULT_SORT_DIRECTION))

        return createPageable(page, size, sortField, direction)
    }

    /**
     * 주어진 기본 URL에 검색 파라미터를 추가하여 쿼리 문자열을 포함한 URL을 생성합니다.
     *
     * @param base 기본 URL입니다.
     * @param searchParams 검색 파라미터의 맵으로, 키는 파라미터 이름이고 값은 파라미터 값입니다.
     * @return 기본 URL에 검색 파라미터가 추가된 완성된 URL 문자열입니다.
     */
    fun buildBaseUrl(base: String, searchParams: Map<String, String?>): String {
        val params = mutableListOf<String>()

        for ((key, value) in searchParams) {
            value?.let { params.add("$key=$it") }
        }

        return if (params.isNotEmpty()) {
            "$base?${params.joinToString("&")}"
        } else {
            base
        }
    }
}
