package com.example.serverarchive.util

enum class ErrorCode(val code: Int, var message: String) {
    UNKNOWN_ERROR(1000, "알 수 없는 오류가 발생했습니다."),
    ALREADY_EXISTS(1001, "이미 존재하는 데이터입니다."),
    INVALID_PARAMETER(1002, "입력한 값이 잘못되었습니다."),
    ;

    companion object {
        fun fromCodeName(codeName: String): ErrorCode {
            return entries.find { it.name == codeName } ?: UNKNOWN_ERROR
        }

        fun fromCode(code: Int): ErrorCode? {
            return entries.find { it.code == code }
        }
    }
}
