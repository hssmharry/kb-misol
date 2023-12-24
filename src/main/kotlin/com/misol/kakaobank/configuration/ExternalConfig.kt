package com.misol.kakaobank.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("external")
class ExternalConfig {
    var kakao: Kakao = Kakao()
    var naver: Naver = Naver()

    class Kakao {
        var defaultUrl: String? = null
        var search: String? = null
        var key: String? = null
    }

    class Naver {
        var defaultUrl: String? = null
        var search: String? = null
        var clientId: String? = null
        var clientSecret: String? = null
    }
}