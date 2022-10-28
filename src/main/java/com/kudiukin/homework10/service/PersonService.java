package com.kudiukin.homework10.service;

import com.kudiukin.homework10.NotFoundException;
import com.kudiukin.homework10.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService extends UserDetailsService {

    Person createPerson(String firstName, String lastName, String email, String phone, String username, String password);

    Person getPersonById(Long id) throws NotFoundException;

    Person getPersonByUsername(String username);

    Person updatePerson(Person person);

    void deletePerson(Long id) throws NotFoundException;

    List<Person> getAllPersons();
}
