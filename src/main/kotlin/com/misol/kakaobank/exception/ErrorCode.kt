package com.misol.kakaobank.exception

enum class ErrorCode(
        val message: String,
) {
    INVALID_PARAMETER("요청 형식이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR("서버 내부 에러가 발생했습니다."),
    EXTERNAL_SERVER_ERROR("외부 서버와의 통신에 실패했습니다."),
}