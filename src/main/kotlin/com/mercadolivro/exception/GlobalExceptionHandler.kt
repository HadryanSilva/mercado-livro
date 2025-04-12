package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException, request: WebRequest): ErrorResponse? {
        return ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = exception.message,
            internalCode = "ML-001",
            errors = null
        )
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(exception: NullPointerException, request: WebRequest): ErrorResponse? {
        return ErrorResponse(
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = exception.message ?: "Null pointer exception",
            internalCode = "ML-002",
            errors = null
        )
    }

}