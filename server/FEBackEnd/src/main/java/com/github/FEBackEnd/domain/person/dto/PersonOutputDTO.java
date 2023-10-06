package com.github.FEBackEnd.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.FEBackEnd.domain.person.entity.PersonEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class PersonOutputDTO {
    @JsonProperty("id")
    @JsonPropertyOrder("1")
    private Long id;

    @JsonProperty("nome")
    @JsonPropertyOrder("2")
    private String name;

    @JsonProperty("email")
    @JsonPropertyOrder("3")
    private String email;

    @JsonProperty("data_nascimento")
    @JsonPropertyOrder("4")
    private LocalDate birthDate;

    @JsonProperty("foto_cadastral")
    @JsonPropertyOrder("5")
    private String photo;

    @JsonProperty("data_atualizacao")
    @JsonPropertyOrder("6")
    private LocalDateTime updatedAt;

    public PersonOutputDTO(PersonEntity person) {
        this(person.getId(), person.getName(), person.getEmail(), person.getBirthDate(), person.getPhoto(), person.getUpdatedAt());
    }

}
