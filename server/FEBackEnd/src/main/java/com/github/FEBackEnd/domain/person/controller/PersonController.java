package com.github.FEBackEnd.domain.person.controller;

import com.github.FEBackEnd.domain.person.dto.PersonInsertInputDTO;
import com.github.FEBackEnd.domain.person.dto.PersonOutputDTO;
import com.github.FEBackEnd.domain.person.dto.PersonUpdateInpuDTO;
import com.github.FEBackEnd.domain.person.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<Page<PersonOutputDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return ResponseEntity.ok(this.service.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> findByID(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByID(id));
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Page<PersonOutputDTO>> findByName(@PageableDefault(size=10, sort={"name"}) Pageable pagination, @PathVariable String name) {
        return ResponseEntity.ok(this.service.findAllByName(pagination, name));
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody @Valid PersonInsertInputDTO data, UriComponentsBuilder uriBuilder) {
        PersonOutputDTO person = this.service.create(data);
        var uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid PersonUpdateInpuDTO data) {
        return ResponseEntity.ok(this.service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
