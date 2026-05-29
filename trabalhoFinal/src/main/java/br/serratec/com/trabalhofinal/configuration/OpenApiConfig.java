package br.serratec.com.trabalhofinal.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Trabalho Final API").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .path("/login", new PathItem().post(new Operation()
                        .addTagsItem("login")
                        .summary("Autenticar usuário e obter token JWT")
                        .requestBody(new RequestBody().content(new Content().addMediaType(
                                "application/json",
                                new MediaType().schema(new Schema<>()
                                        .addProperty("username", new Schema<String>().type("string").example("seu@email.com"))
                                        .addProperty("password", new Schema<String>().type("string").example("suaSenha"))))))
                        .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                .description("Token JWT gerado")
                                .content(new Content().addMediaType("application/json",
                                        new MediaType().schema(new Schema<>()
                                                .addProperty("token", new Schema<String>().type("string")))))))));
        }
}