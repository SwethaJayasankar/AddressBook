package com.addressbook.mappers;
import com.addressbook.models.Person;
	import com.addressbook.dtos.PersonDTO;

	import java.util.List;
	import java.util.stream.Collectors;

	public class PersonMapper {

	    public static Person toEntity(PersonDTO dto) {
	        Person person = new Person();
	        person.setId(dto.getId());  
	        person.setName(dto.getName());
	        person.setEmail(dto.getEmail());
	        person.setPhone(dto.getPhone());
	        person.setAddress(dto.getAddress());
	        return person;
	    }

	    public static PersonDTO toDTO(Person person) {
	        PersonDTO dto = new PersonDTO();
	        dto.setId(person.getId());
	        dto.setName(person.getName());
	        dto.setEmail(person.getEmail());
	        dto.setPhone(person.getPhone());
	        dto.setAddress(person.getAddress());
	        return dto;
	    }

	    public static List<Person> toEntityList(List<PersonDTO> dtoList) {
	        return dtoList.stream()
	                      .map(PersonMapper::toEntity)
	                      .collect(Collectors.toList());
	    }

	    public static List<PersonDTO> toDTOList(List<Person> personList) {
	        return personList.stream()
	                         .map(PersonMapper::toDTO)
	                         .collect(Collectors.toList());
	    }
	}

