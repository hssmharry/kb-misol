package com.misol.kakaobank.infrastructure.webclient

import com.misol.kakaobank.exception.CustomException
import com.misol.kakaobank.exception.ErrorCode
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URI
import java.util.function.Consumer

@Service
class SearchClient {
    fun <T : Any> apiSearch(defaultUrl: String, uriBuilder: URI, headers: Consumer<HttpHeaders>, responseType: Class<T>): T? {
        val webClient = WebClient.builder()
                .baseUrl(defaultUrl)
                .defaultHeaders(headers)
                .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) }
                .build()

        val result = webClient.get()
                .uri(uriBuilder)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume { throwable ->
                    throwable.printStackTrace()
                    Mono.error(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EXTERNAL_SERVER_ERROR, null))
                }

        return result.block()
    }
}