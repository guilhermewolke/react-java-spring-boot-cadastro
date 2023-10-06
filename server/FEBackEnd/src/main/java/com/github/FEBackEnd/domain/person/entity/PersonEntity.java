package com.github.FEBackEnd.domain.person.entity;

import com.github.FEBackEnd.domain.person.dto.PersonInsertInputDTO;
import com.github.FEBackEnd.domain.person.dto.PersonUpdateInpuDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name="Person")
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name="birthdate")
    private LocalDate birthDate;

    private String photo;
    @Column(name="updatedat")
    private LocalDateTime updatedAt;

    public PersonEntity(PersonInsertInputDTO data) {
        this.name = data.name();
        this.email = data.email();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthDate = LocalDate.parse(data.birthDate(), formatter);
        this.photo = data.photo();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(PersonUpdateInpuDTO data) {

        if (data.name() != null && !this.getName().equals(data.name())) {
            this.name = data.name();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (data.birthDate() != null && !this.getBirthDate().format(formatter).equals(data.birthDate())) {
            this.birthDate = LocalDate.parse(data.birthDate(), formatter);
        }

        if (data.photo() != null && !this.getPhoto().equals(data.photo())) {
            this.photo = data.photo();
        }

        this.updatedAt = LocalDateTime.now();
    }
}
