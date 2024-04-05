package com.raccon.simplecrud.utils.SwaggerAnotations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.raccon.simplecrud.dto.AddressDTO;

public class AddressAnnotations {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Lista Endereços")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface GetAllAddressRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Cria Endereço")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        @RequestBody(description = "Dados do endereço a ser criado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class), examples = @ExampleObject(value = "{\n"
                        +
                        "  \"personId\": 1,\n" +
                        "  \"addressId\": \"Rua ABC\",\n" +
                        "  \"city\": \"Cidade XYZ\",\n" +
                        "  \"state\": \"Estado XYZ\",\n" +
                        "  \"postalCode\": \"12345-678\"\n" +
                        "}")))
        public @interface CreateAddressRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Altera Endereço")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Endereço alterado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        @RequestBody(description = "Dados do endereço a ser alterado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class), examples = @ExampleObject(value = "{\n"
                        +
                        "  \"personId\": 1,\n" +
                        "  \"street\": \"Rua ABC\",\n" +
                        "  \"city\": \"Cidade XYZ\",\n" +
                        "  \"state\": \"Estado XYZ\",\n" +
                        "  \"postalCode\": \"12345-678\"\n" +
                        "}")))
        public @interface PutAddressRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Busca Endereço pelo ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Endereço encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface GetByIdAddressRequestBody {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @Operation(summary = "Remover Endereço")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(mediaType = "application/json")),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json"))
        })
        public @interface DeleteByIdAddressRequestBody {
        }

}
