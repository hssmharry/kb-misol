package com.misol.kakaobank.domain.blogs.enums

enum class SearchServiceType {
    KAKAO,
    NAVER,
    ;

    fun getOtherServiceType(): SearchServiceType {
        return when (this) {
            KAKAO -> NAVER
            NAVER -> KAKAO
        }
    }
}