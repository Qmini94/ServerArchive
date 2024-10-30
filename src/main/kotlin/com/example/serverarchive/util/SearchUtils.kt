package com.example.serverarchive.util

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

object SearchUtils {
    /**
     * 주어진 검색 파라미터와 날짜 범위에 따라 JPA Specification을 생성합니다.
     *
     * @param searchParams 검색 기준을 포함하는 맵. 키는 필드 이름, 값은 검색 값입니다.
     * @param startDate 결과 필터링을 위한 시작 날짜. null일 수 있습니다.
     * @param endDate 결과 필터링을 위한 종료 날짜. null일 수 있습니다.
     * @return 주어진 기준에 따라 결과를 필터링하는 데 사용할 수 있는 Specification<T>입니다.
     */
    fun <T> createSpecification(
        searchParams: Map<String, String?>,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?
    ): Specification<T> {
        return Specification { root: Root<T>, _, criteriaBuilder: CriteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            searchParams.forEach { (key, value) ->
                if (!value.isNullOrBlank()) {
                    predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(key)),
                        "%${value.lowercase()}%"
                    ))
                }
            }
            when {
                startDate != null && endDate != null -> {
                    predicates.add(criteriaBuilder.between(
                        root.get("createdDate"),
                        startDate,
                        endDate
                    ))
                }
                startDate != null -> {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdDate"),
                        startDate
                    ))
                }
                endDate != null -> {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdDate"),
                        endDate
                    ))
                }
            }
            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

    /**
     * 선택된 옵션에 따라 검색 파라미터를 쿼리 형식으로 매핑합니다.
     *
     * @param searchKey 필터링에 사용되는 검색 키워드입니다.
     * @param searchOptions 사용자가 선택한 검색 옵션의 리스트입니다.
     * @param options 유효성을 검증할 수 있는 열거형 옵션 배열입니다.
     * @param fieldNameSelector 열거형에서 필드 이름을 추출하는 람다 함수입니다.
     * @return 필드 이름을 키로 하고 검색 키워드를 값으로 하는 맵입니다.
     */
    fun <T : Enum<T>> mapSearchParams(
        searchKey: String,
        searchOptions: List<String>,
        options: Array<T>,
        fieldNameSelector: (T) -> String
    ): Map<String, String> {
        return searchOptions
            .filter { selectedOption ->
                options.any { fieldNameSelector(it).equals(selectedOption, ignoreCase = true) }
            }
            .associateWith { searchKey }
    }

    fun isValidSearchKey(searchKey: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9가-힣]{1,100}$")
        return regex.matches(searchKey)
    }
}
