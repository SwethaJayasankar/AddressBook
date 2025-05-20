package com.addressbook.controller;

import com.addressbook.models.Person;
import com.addressbook.dtos.PersonDTO;
import com.addressbook.mappers.PersonMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private List<Person> personList = new ArrayList<>();
    private long counter = 1;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> dtos = personList.stream()
                .map(PersonMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable long id) {
        return personList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(p -> ResponseEntity.ok(PersonMapper.toDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.toEntity(personDTO);
        person.setId(counter++);  
        personList.add(person);
        return ResponseEntity.ok(PersonMapper.toDTO(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable long id, @RequestBody PersonDTO updatedDTO) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId() == id) {
                Person updatedPerson = PersonMapper.toEntity(updatedDTO);
                updatedPerson.setId(id);
                personList.set(i, updatedPerson);
                return ResponseEntity.ok(PersonMapper.toDTO(updatedPerson));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        boolean removed = personList.removeIf(p -> p.getId() == id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}


