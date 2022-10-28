package com.kudiukin.homework10.facade;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.dto.PersonDto;

import java.util.List;

public interface PersonFacade {

    PersonDto createPerson(PersonDto personDto);

    PersonDto getPersonById(PersonDto personDto) throws NotFoundException;

    PersonDto updatePerson(PersonDto personDto);

    void deletePerson(PersonDto personDto) throws NotFoundException;

    List<PersonDto> getAllPersons();

}
