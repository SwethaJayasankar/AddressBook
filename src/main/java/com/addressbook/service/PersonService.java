package com.addressbook.service;
import com.addressbook.models.Person;
	import com.addressbook.dtos.PersonDTO;
	import com.addressbook.mappers.PersonMapper;

	import org.springframework.stereotype.Service;

	import java.util.*;
	import java.util.stream.Collectors;

	@Service
	public class PersonService {

	    private final List<Person> personList = new ArrayList<>();
	    private long counter = 1;

	    public List<PersonDTO> getAllPersons() {
	        return personList.stream()
	                .map(PersonMapper::toDTO)
	                .collect(Collectors.toList());
	    }

	    public Optional<PersonDTO> getPersonById(long id) {
	        return personList.stream()
	                .filter(p -> p.getId() == id)
	                .findFirst()
	                .map(PersonMapper::toDTO);
	    }

	    public PersonDTO addPerson(PersonDTO dto) {
	        Person person = PersonMapper.toEntity(dto);
	        person.setId(counter++);
	        personList.add(person);
	        return PersonMapper.toDTO(person);
	    }

	    public Optional<PersonDTO> updatePerson(long id, PersonDTO updatedDTO) {
	        for (int i = 0; i < personList.size(); i++) {
	            if (personList.get(i).getId() == id) {
	                Person updatedPerson = PersonMapper.toEntity(updatedDTO);
	                updatedPerson.setId(id);
	                personList.set(i, updatedPerson);
	                return Optional.of(PersonMapper.toDTO(updatedPerson));
	            }
	        }
	        return Optional.empty();
	    }

	    public boolean deletePerson(long id) {
	        return personList.removeIf(p -> p.getId() == id);
	    }
	}


