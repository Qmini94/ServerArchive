package com.example.serverarchive.util

/**
 * TODO :: Define Error Code
 * user - 10
 * board - 20
 * server - 30
 * **/

object ErrorCodes {
	private val errorMap = mapOf(
		1001 to "아이디를 입력하세요.",
		1002 to "이름을 입력하세요.",
		1003 to "비밀번호를 입력하세요.",
		1004 to "사용 중인 아이디입니다.",
		1005 to "회원정보가 존재하지 않습니다",
		1006 to "패스워드가 일치하지 않습니다",
	)

	fun getMessage(code: Int): String {
		return errorMap[code] ?: "알 수 없는 오류입니다. 관리자에게 문의하세요"
	}
}