package com.mercadolivro.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                io.swagger.v3.oas.models.info.Info()
                    .title("Mercado Livre API")
                    .version("v1")
                    .description("API for managing customers and products")
            )
    }

}