package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = exception.message,
            internalCode = "ML-001",
            errors = null
        )

        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(exception: NullPointerException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val response =  ErrorResponse(
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = exception.message ?: "Null pointer exception",
            internalCode = "ML-002",
            errors = null
        )

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}