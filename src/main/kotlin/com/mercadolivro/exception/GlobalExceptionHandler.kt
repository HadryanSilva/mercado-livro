package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import com.mercadolivro.controller.response.FieldErrorResponse
import com.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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
            internalCode = exception.errorCode,
            errors = null
        )

        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = exception.message,
            internalCode = Errors.ML101.code,
            errors = null
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException, request: WebRequest):
            ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = Errors.ML998.message,
            internalCode = Errors.ML101.code,
            errors = exception.bindingResult.fieldErrors
                .map { FieldErrorResponse(it.defaultMessage ?: "invalid", field = it.field) }
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(exception: NullPointerException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val response =  ErrorResponse(
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = Errors.ML101.message,
            internalCode = Errors.ML999.code,
            errors = null
        )

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}