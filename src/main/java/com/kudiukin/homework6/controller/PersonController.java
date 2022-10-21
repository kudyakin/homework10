package com.kudiukin.homework6.controller;

import com.kudiukin.homework6.converter.PersonConverter;
import com.kudiukin.homework6.dto.PersonDto;
import com.kudiukin.homework6.service.PersonService;
import com.kudiukin.homework6.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.kudiukin.homework6.converter.PersonConverter.convertPersonDto2PersonModel;
import static com.kudiukin.homework6.converter.PersonConverter.convertPersonModel2PersonDto;

@Controller
@RequestMapping(path="/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPerson(@ModelAttribute("person") PersonDto personDto){
        personService.createPerson(convertPersonDto2PersonModel(personDto));
        return "/person/createPersonSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getPersonById(@ModelAttribute("personById") PersonDto personDto, Model model) throws NotFoundException {
        PersonDto personById = convertPersonModel2PersonDto(personService.getPersonById(personDto.getId()));
        model.addAttribute("personById", personById);
        return "/person/getPersonSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") PersonDto personDto) {
        personService.updatePerson(convertPersonDto2PersonModel(personDto));
        return "/person/updatePersonSuccess";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deletePerson(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        personService.deletePerson(personDto.getId());
        return "/person/deletePersonSuccess";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPersonView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/createPerson";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getPersonByIdView(Model model) {
        model.addAttribute("personById", new PersonDto());
        return "/person/getPerson";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePersonView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/updatePerson";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePersonView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/deletePerson";
    }

    @GetMapping( "/all")
    public String getAllPersons(Model model) {
        model.addAttribute("all", personService.getAllPersons().stream()
                .map(PersonConverter::convertPersonModel2PersonDto).collect(Collectors.toList()));
        return "/person/allPersons";
    }
}