package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.converter.PersonConverter;
import com.kudiukin.homework10.dto.PersonDto;
import com.kudiukin.homework10.model.Person;
import com.kudiukin.homework10.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.kudiukin.homework10.converter.PersonConverter.convertPersonDto2PersonModel;
import static com.kudiukin.homework10.converter.PersonConverter.convertPersonModel2PersonDto;

@Component
public class PersonFacadeImpl implements PersonFacade{

    @Autowired
    private PersonService personService;

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person newPerson = personService.createPerson(personDto.getFirstName(), personDto.getLastName(),
                personDto.getEmail(), personDto.getPhone(), personDto.getUsername(), personDto.getPassword());
        return convertPersonModel2PersonDto(newPerson);
    }

    @Override
    public PersonDto getPersonById(PersonDto personDto) throws NotFoundException {
        Person getPerson = personService.getPersonById(personDto.getId());
        return convertPersonModel2PersonDto(getPerson);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        Person personBeforeUpdate = convertPersonDto2PersonModel(personDto);
        Person updatedPerson = personService.updatePerson(personBeforeUpdate);
        return convertPersonModel2PersonDto(updatedPerson);
    }

    @Override
    public void deletePerson(PersonDto personDto) throws NotFoundException {
        personService.deletePerson(personDto.getId());
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons().stream().map(PersonConverter::convertPersonModel2PersonDto)
                .collect(Collectors.toList());
    }

}
