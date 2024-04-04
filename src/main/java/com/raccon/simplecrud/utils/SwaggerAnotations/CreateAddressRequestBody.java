package com.raccon.simplecrud.utils.SwaggerAnotations;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.raccon.simplecrud.dto.AddressDTO;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RequestBody(description = "Dados do endereço a ser criado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class), examples = @ExampleObject(value = "{\n"
        +
        "  \"personId\": 1,\n" +
        "  \"street\": \"Rua ABC\",\n" +
        "  \"city\": \"Cidade XYZ\",\n" +
        "  \"state\": \"Estado XYZ\",\n" +
        "  \"postalCode\": \"12345-678\"\n" +
        "}")))
public @interface CreateAddressRequestBody {
}
