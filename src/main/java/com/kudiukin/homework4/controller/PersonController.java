package com.kudiukin.homework4.controller;

import com.kudiukin.homework4.model.Person;
import com.kudiukin.homework4.service.PersonService;
import com.kudiukin.homework4.utils.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path="/shop/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Person getPersonById(@RequestParam Integer id) throws NotFoundException {
        return personService.getPersonById(id);
    }

    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public Person updatePerson(@RequestParam Integer id, @RequestBody Person person) throws NotFoundException {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@RequestParam Integer id) throws NotFoundException {
        personService.deletePerson(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Person> getAll() {
        return personService.getAllPersons();
    }
}