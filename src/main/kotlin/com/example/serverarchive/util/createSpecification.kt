package com.example.serverarchive.util

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

object SpecificationUtils {

    fun <T> createSpecification(
        searchParams: Map<String, String?>,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?
    ): Specification<T> {
        return Specification { root: Root<T>, query, criteriaBuilder: CriteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            searchParams.forEach { (key, value) ->
                if (!value.isNullOrBlank()) {
                    predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get<String>(key)),
                        "%${value.lowercase()}%"
                    ))
                }
            }

            if (startDate != null && endDate != null) {
                predicates.add(criteriaBuilder.between(
                    root.get<LocalDateTime>("createdDate"),
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
        val searchCriteria = mutableMapOf<String, String>()

        searchOptions.forEach { selectedOption ->
            val option = options.find { fieldNameSelector(it).equals(selectedOption, ignoreCase = true) }
            option?.let {
                searchCriteria[fieldNameSelector(it)] = searchKey
            }
        }

        return searchCriteria
    }
}
