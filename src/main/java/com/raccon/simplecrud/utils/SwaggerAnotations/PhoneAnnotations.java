package com.raccon.simplecrud.utils.SwaggerAnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.raccon.simplecrud.dto.PhoneDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class PhoneAnnotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Lista Telefones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de telefones retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public @interface GetAllPhoneRequestBody {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Cria telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "telefone criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @RequestBody(description = "Dados do telefone a ser criado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class), examples = @ExampleObject(value = "{\n"
            +
            "  \"personId\": 1,\n" +
            "  \"phone\": \"123-456-7890\"\n" +
            "}")))
    public @interface CreatePhoneRequestBody {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Altera telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "telefone alterado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "telefone não encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    @RequestBody(description = "Dados do telefone a ser alterado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class), examples = @ExampleObject(value = "{\n"
            +
            "  \"personId\": 1,\n" +
            "  \"phone\": \"123-456-7890\"\n" +
            "}")))
    public @interface PutPhoneRequestBody {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Busca telefone pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "telefone encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "telefone não encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public @interface GetByIdPhoneRequestBody {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Remover telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "telefone removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "telefone não encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
    })
    public @interface DeleteByIdPhoneRequestBody {
    }
}
