package com.github.FEBackEnd.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import java.util.Date;

public record PersonUpdateInpuDTO(
        @NotNull
        Long id,
        @JsonProperty("nome")
        String name,

        @JsonProperty("email")
        String email,

        @JsonProperty("data_nascimento")
        @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2}")
        String birthDate,

        @JsonProperty("foto_cadastral")
        String photo) {
}
