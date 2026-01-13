package com.saborurbano.restaurante.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Restaurante Sabor Urbano", version = "1.0.0", 
description = "Documentación para el API de Restaurante Sabor Urbano"))

public class OpenApiConfig {
    
}
