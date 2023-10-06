package com.github.FEBackEnd.domain.person.service;

import com.github.FEBackEnd.domain.person.dto.PersonInsertInputDTO;
import com.github.FEBackEnd.domain.person.repository.PersonRepository;
import com.github.FEBackEnd.domain.person.dto.PersonOutputDTO;
import com.github.FEBackEnd.domain.person.dto.PersonUpdateInpuDTO;
import com.github.FEBackEnd.domain.person.entity.PersonEntity;
import com.github.FEBackEnd.shared.mail.dto.MailerConfigDTO;
import com.github.FEBackEnd.shared.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private MailService mailer;

    public PersonOutputDTO create(PersonInsertInputDTO data) {
        PersonEntity entity = new PersonEntity(data);
        this.repository.save(entity);

        MailerConfigDTO mailOptions = new MailerConfigDTO(data.email(), "Confirmação de Cadastro", "welcome");
        PersonOutputDTO output = new PersonOutputDTO(entity);
        this.mailer.sendHTMLEmail(mailOptions, output);
        return output;
    }

    public Page<PersonOutputDTO> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(PersonOutputDTO::new);
    }

    public PersonOutputDTO update(PersonUpdateInpuDTO data) {
        PersonEntity entity = this.repository.getReferenceById(data.id());
        entity.update(data);

        return new PersonOutputDTO(this.repository.save(entity));
    }

    public PersonOutputDTO findByID(Long id) {
        PersonEntity entity = this.repository.getReferenceById(id);
        return new PersonOutputDTO(entity);
    }

    public void delete (Long id) {
        PersonEntity entity = this.repository.getReferenceById(id);
        this.repository.delete(entity);
    }

    public Page<PersonOutputDTO> findAllByName(Pageable pagination, String name) {
        return this.repository.findByNameContaining(name, pagination).map(PersonOutputDTO::new);
    }
}
