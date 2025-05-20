package com.addressbook.service;

import com.addressbook.models.Person;
import com.addressbook.dtos.PersonDTO;
import com.addressbook.mappers.PersonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    private final List<Person> personList = new ArrayList<>();
    private long counter = 1;
    
    @Autowired
    private final PersonMapper personMapper;

    
    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public List<PersonDTO> getAllPersons() {
        return personMapper.toDTOList(personList);
    }

    public Optional<PersonDTO> getPersonById(long id) {
        return personList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(personMapper::toDTO);
    }

    public PersonDTO addPerson(PersonDTO dto) {
        Person person = personMapper.toEntity(dto);
        person.setId(counter++);
        personList.add(person);
        return personMapper.toDTO(person);
    }

    public Optional<PersonDTO> updatePerson(long id, PersonDTO updatedDTO) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId() == id) {
                Person updatedPerson = personMapper.toEntity(updatedDTO);
                updatedPerson.setId(id);
                personList.set(i, updatedPerson);
                return Optional.of(personMapper.toDTO(updatedPerson));
            }
        }
        return Optional.empty();
    }

    public boolean deletePerson(long id) {
        return personList.removeIf(p -> p.getId() == id);
    }
}

