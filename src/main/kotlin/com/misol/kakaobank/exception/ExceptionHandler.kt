package com.misol.kakaobank.exception

import com.misol.kakaobank.common.MisolResponse
import jakarta.validation.ConstraintViolationException
import lombok.extern.slf4j.Slf4j
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@Slf4j
@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(CustomException::class)
    protected fun handleBaseException(e: CustomException): ResponseEntity<MisolResponse> {
        log.error("# Custom Exception : ", e)
        return ResponseEntity
                .status(e.status)
                .body(MisolResponse(e.code.name, e.code.message, e.result))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleBaseException(e: Exception): ResponseEntity<MisolResponse> {
        log.error("# Server Exception : ", e)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MisolResponse(ErrorCode.INTERNAL_SERVER_ERROR.name, ErrorCode.INTERNAL_SERVER_ERROR.message, e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<MisolResponse> {
        log.warn("# invalid parameter] : ", e)
        val bindingResult = e.bindingResult
        val violationException = e.cause as? ConstraintViolationException

        val errorMessage = if (violationException != null) {
            violationException.constraintViolations.firstOrNull()?.message
        } else {
            bindingResult.fieldError?.defaultMessage
        } ?: ErrorCode.INVALID_PARAMETER.message

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(MisolResponse(ErrorCode.INVALID_PARAMETER.name,
                        errorMessage,
                        e.bindingResult.fieldError?.field))
    }
}
