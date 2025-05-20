package com.addressbook.mappers;

import com.addressbook.models.Person;
import com.addressbook.dtos.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public Person toEntity(PersonDTO dto) {
        return new Person(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAddress());
    }

    public PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getEmail(), person.getPhone(), person.getAddress());
    }

    public List<PersonDTO> toDTOList(List<Person> persons) {
        return persons.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
