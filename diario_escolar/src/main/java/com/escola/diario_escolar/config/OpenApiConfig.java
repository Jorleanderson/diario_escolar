package com.escola.diario_escolar.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Diário Escolar",
        version = "1.0.0",
        description = """
            API REST para gerenciamento de turmas, alunos, professores,
            disciplinas, notas e geração de boletim escolar.
        """,
        contact = @Contact(
            name = "Jorleanderson Moraes Maia",
            email = "jorleanderson.pro@gmail.com"
        )
    )
)
public class OpenApiConfig {
}

