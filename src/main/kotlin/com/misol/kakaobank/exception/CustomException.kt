package com.misol.kakaobank.exception

import org.springframework.http.HttpStatus

class CustomException(
        val status: HttpStatus,
        val code: ErrorCode,
        val result: Any?,
) : RuntimeException() {
}