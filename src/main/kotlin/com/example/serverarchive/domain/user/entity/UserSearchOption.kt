package com.example.serverarchive.domain.user.entity

enum class UserSearchOption(val fieldName: String, val label: String) {
	NAME("user_name", "회원명"),
	LEVEL("level", "권한"),
	DEPARTMENT("department", "부서명"),

}