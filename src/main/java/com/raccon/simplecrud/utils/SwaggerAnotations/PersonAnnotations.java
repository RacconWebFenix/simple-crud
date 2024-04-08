package com.raccon.simplecrud.utils.SwaggerAnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.raccon.simplecrud.dto.PersonDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class PersonAnnotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Criar Pessoa", description = "Cria uma nova pessoa com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @RequestBody(description = "Dados da pessoa a ser criada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class), examples = @ExampleObject(value = "{\n"
            +
            "  \"name\": \"John Doe\",\n" +
            "  \"cpf\": \"12345678910\",\n" +
            "  \"birthDate\": \"1990-05-20\",\n" +
            "  \"email\": \"johndoe@example.com\",\n" +
            "  \"personFunction\": \"COOPERADOR\",\n" +
            "  \"instrument\": \"PIANO\",\n" +
            "  \"address\": [\n" +
            "    {\n" +
            "      \"street\": \"123 Main St\",\n" +
            "      \"city\": \"Anytown\",\n" +
            "      \"state\": \"Anystate\",\n" +
            "      \"postalCode\": \"12345\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"street\": \"456 Oak St\",\n" +
            "      \"city\": \"Othertown\",\n" +
            "      \"state\": \"Anotherstate\",\n" +
            "      \"postalCode\": \"54321\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"phoneNumbers\": [\n" +
            "    {\n" +
            "      \"phone\": \"123-456-7890\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"phone\": \"987-654-3210\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"congregations\": [\n" +
            "    {\n" +
            "      \"name\": \"First Congregation\",\n" +
            "      \"address\": {\n" +
            "        \"street\": \"789 Elm St\",\n" +
            "        \"city\": \"Sometown\",\n" +
            "        \"state\": \"Somestate\",\n" +
            "        \"postalCode\": \"67890\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Second Congregation\",\n" +
            "      \"address\": {\n" +
            "        \"street\": \"321 Pine St\",\n" +
            "        \"city\": \"Anothertown\",\n" +
            "        \"state\": \"Yetanotherstate\",\n" +
            "        \"postalCode\": \"09876\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}")))
    public @interface CreatePersonRequestBody {
    }
}
