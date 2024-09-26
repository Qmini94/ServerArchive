package com.example.serverarchive.api.exception

import com.example.serverarchive.api.response.ErrorData
import com.example.serverarchive.api.response.ErrorResponse
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.Logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHandler {
    private val logger by Logger()

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(req: HttpServletRequest, ex: Exception): ErrorResponse {
        val errorCode = ErrorCode.fromCodeName(ex.localizedMessage)

        logger.error("REQUEST URI: ${req.requestURI}, Message: ${ex.localizedMessage}")

        return ErrorResponse(
            message = ex.localizedMessage,
            error = ErrorData(
                code = errorCode.code,
                message = errorCode.message,
            )
        )
    }
}