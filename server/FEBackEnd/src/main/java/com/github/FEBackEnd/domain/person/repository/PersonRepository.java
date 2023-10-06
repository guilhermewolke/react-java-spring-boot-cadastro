package com.github.FEBackEnd.domain.person.repository;

import com.github.FEBackEnd.domain.person.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Page<PersonEntity> findByNameContaining(String name, Pageable pagination);
}
