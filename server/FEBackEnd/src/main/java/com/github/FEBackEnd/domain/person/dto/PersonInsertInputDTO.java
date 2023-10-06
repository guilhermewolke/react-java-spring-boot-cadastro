package com.github.FEBackEnd.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.Date;

public record PersonInsertInputDTO(
        @NotBlank
        @NotNull
        @JsonProperty("nome")
        String name,

        @NotBlank
        @NotNull
        @JsonProperty("email")
        @Email
        String email,

        @NotNull
        @NotBlank
        @JsonProperty("data_nascimento")
        @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2}")
        String birthDate,

        @NotNull
        @JsonProperty("foto_cadastral")
        String photo) {
}
