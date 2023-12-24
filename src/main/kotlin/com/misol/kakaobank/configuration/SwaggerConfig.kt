package com.misol.kakaobank.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@OpenAPIDefinition(info = Info(title = "[박미솔] 카카오뱅크 기술과제", description = "블로그 검색 API 명세", version = "v1"))
@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securityScheme: SecurityScheme = SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .`in`(SecurityScheme.In.HEADER).name("Authorization")
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList("bearerAuth")
        return OpenAPI()
                .components(Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
    }
}