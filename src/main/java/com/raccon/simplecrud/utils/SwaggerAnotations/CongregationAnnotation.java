package com.raccon.simplecrud.utils.SwaggerAnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.raccon.simplecrud.dto.CongregationDTO;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class CongregationAnnotation {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Lista Congregações")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de congregações retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface GetAllCongregationRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Criar Congregação", description = "Cria uma nova congregação com os dados fornecidos.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Congregação criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        @RequestBody(description = "Dados da congregação a ser criada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class), examples = @ExampleObject(value = "{\n"
                        +
                        "  \"name\": \"Congregação ABC\",\n" +
                        "  \"addressId\": 0,\n" +
                        "  \"addressDetails\": {\n" +
                        "    \"personId\": 0,\n" +
                        "    \"street\": \"Ruadddd ABC\",\n" +
                        "    \"city\": \"Cidade XYZ\",\n" +
                        "    \"state\": \"Estado A\",\n" +
                        "    \"postalCode\": \"123456\"\n" +
                        "  },\n" +
                        "  \"memberIds\": [1, 2, 3]\n" +
                        "}")))
        public @interface CreateCongregationRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Editar Congregação", description = "Edita congregação com os dados fornecidos.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Congregação editada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        @RequestBody(description = "Dados da congregação a ser criada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class), examples = @ExampleObject(value = "{\n"
                        +
                        "  \"name\": \"Congregação ABC\",\n" +
                        "  \"addressId\": 0,\n" +
                        "  \"addressDetails\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"personId\": 0,\n" +
                        "    \"street\": \"Ruadddd ABC\",\n" +
                        "    \"city\": \"Cidade XYZ\",\n" +
                        "    \"state\": \"Estado A\",\n" +
                        "    \"postalCode\": \"123456\"\n" +
                        "  },\n" +
                        "  \"memberIds\": [1, 2, 3]\n" +
                        "}")))
        public @interface PutCongregationRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Buscar Congregação", description = "Busca congregação com os dados fornecidos.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Congregação editada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongregationDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface GetByIdCongregationRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Remover Congregação")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Congregação removido com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "Congregação não encontrado", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface DeleteCongregationRequestBody {
        }

}
