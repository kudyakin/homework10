package com.kudiukin.homework10.service;

import com.kudiukin.homework10.model.Person;
import com.kudiukin.homework10.model.Role;
import com.kudiukin.homework10.repository.PersonRepository;
import com.kudiukin.homework10.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(String firstName, String lastName, String email, String phone, String username, String password) {
        Person newPerson = new Person();
        newPerson.setFirstName(firstName);
        newPerson.setLastName(lastName);
        newPerson.setEmail(email);
        newPerson.setPhone(phone);
        newPerson.setUsername(username);
        newPerson.setPassword(bCryptPasswordEncoder.encode(password));
        if (newPerson.getUsername().contains("admin")) {
            newPerson.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        } else {
            newPerson.setRoles(Collections.singleton(new Role(1L, "ROLE_CUSTOMER")));
        }
            return personRepository.save(newPerson);
    }

    @Override
    public Person getPersonById(Long id) throws NotFoundException {
        if (personRepository.findById(id).isPresent()) {
            return personRepository.findById(id).get();
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public void deletePerson(Long id) throws NotFoundException {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.findById(person.getId())
                .map(entity -> {
                    entity.setFirstName(person.getFirstName());
                    entity.setLastName(person.getLastName());
                    entity.setEmail(person.getEmail());
                    entity.setPhone(person.getPhone());
                    personRepository.save(entity);
                    return entity;
                })
                .orElseThrow(() -> new EntityNotFoundException("Not Found id = " + person.getId()));
    }

    @Override
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = getPersonByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return person;
    }

    @Override
    public Person getPersonByUsername(String username) {
        if (personRepository.findPersonByUsername(username) != null) {
            return personRepository.findPersonByUsername(username);
        } else {
            try {
                throw new NotFoundException("Person with username " + username + " is not found");
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
