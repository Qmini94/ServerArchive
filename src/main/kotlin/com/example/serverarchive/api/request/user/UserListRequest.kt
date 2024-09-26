package com.example.serverarchive.api.request.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 목록 출력")
data class UserListRequest(
	@Schema(description = "페이지", nullable = false, required = true)
	val page: Int,

	@Schema(description = "페이지 출력 수", nullable = false, required = true)
	val size: Int,

	@Schema(description = "검색키", nullable = true, required = false)
	var searchKey: String,

	@Schema(description = "검색어", nullable = true, required = false)
	val keyword: String? = null,
)