package com.misol.kakaobank.domain.blogs.enums

enum class SortingType(val kakao: String, val naver: String) {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date"),
    ;
}