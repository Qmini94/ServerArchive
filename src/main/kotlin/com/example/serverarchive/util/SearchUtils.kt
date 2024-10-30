package com.example.serverarchive.util

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

object SearchUtils {
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

            if (startDate != null && endDate != null) {
                predicates.add(criteriaBuilder.between(
                    root.get("createdDate"),
                    startDate,
                    endDate
                ))
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

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
