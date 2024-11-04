package com.example.serverarchive.domain.customer.entity

enum class CustomerSearchOption(val fieldName: String, val label: String) {
    NAME("name", "업체명"),
    MEMO("memo", "메모")
}