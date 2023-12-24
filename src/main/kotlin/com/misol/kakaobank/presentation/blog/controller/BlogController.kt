package com.misol.kakaobank.presentation.blog.controller

import com.misol.kakaobank.application.blog.SearchService
import com.misol.kakaobank.application.blog.model.PopularKeywordResponseModel
import com.misol.kakaobank.application.blog.model.SearchResponseItemModel
import com.misol.kakaobank.common.MisolResponse
import com.misol.kakaobank.presentation.blog.model.BlogSearchRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/blogs")
class BlogController(private val searchService: SearchService) {

    @GetMapping
    @Operation(summary = "블로그 조회", description = "블로그를 검색합니다. (카카오 API가 실패하면 NAVER API로 대체)")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = SearchResponseItemModel::class)))),
        ApiResponse(responseCode = "400", description = "잘못된 요청", content = arrayOf(Content(schema = Schema(implementation = MisolResponse::class)))),
        ApiResponse(responseCode = "500", description = "서버 에러", content = arrayOf(Content(schema = Schema(implementation = MisolResponse::class))))])
    fun search(@Valid @ParameterObject request: BlogSearchRequest): ResponseEntity<Page<SearchResponseItemModel>> =
            searchService.search(request.toModel()).let { result -> ResponseEntity.ok(result) }

    // 인기 검색어 조회
    @GetMapping("/popular")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "성공", content = arrayOf(Content(schema = Schema(implementation = PopularKeywordResponseModel::class)))),
        ApiResponse(responseCode = "400", description = "잘못된 요청", content = arrayOf(Content(schema = Schema(implementation = MisolResponse::class)))),
        ApiResponse(responseCode = "500", description = "서버 에러", content = arrayOf(Content(schema = Schema(implementation = MisolResponse::class))))])
    fun popular(): ResponseEntity<List<PopularKeywordResponseModel>> =
            searchService.popular().let { popular -> ResponseEntity.ok(popular) }
}