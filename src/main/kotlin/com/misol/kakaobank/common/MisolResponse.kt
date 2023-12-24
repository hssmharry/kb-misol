package com.misol.kakaobank.common

import org.springframework.http.HttpStatus

data class MisolResponse(
        val code: String,
        val message: String,
        val result: Any?,
)