package com.kudiukin.homework10.controller;

import com.kudiukin.homework10.dto.PersonDto;
import com.kudiukin.homework10.facade.PersonFacade;
import com.kudiukin.homework10.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/api/person")
@Slf4j
public class PersonController {

    private final PersonFacade personFacade;

    private final HttpServletRequest httpServletRequest;

    public PersonController(PersonFacade personFacade, HttpServletRequest httpServletRequest) {
        this.personFacade = personFacade;
        this.httpServletRequest = httpServletRequest;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPerson(@ModelAttribute("person") PersonDto personDto){
        personFacade.createPerson(personDto);
        log.info("New person is created with username [{}] and email [{}]", personDto.getUsername(), personDto.getEmail());
        return "/person/createPersonSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getPersonById(@ModelAttribute("personById") PersonDto personDto, Model model) throws NotFoundException {
        PersonDto personById = personFacade.getPersonById(personDto);
        model.addAttribute("personById", personById);
        return "/person/getPersonSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") PersonDto personDto) {
        personFacade.updatePerson(personDto);
        log.info("Person with ID [{}] is updated", personDto.getId());
        return "/person/updatePersonSuccess";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deletePerson(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        personFacade.deletePerson(personDto);
        log.info("Person with ID [{}] is deleted", personDto.getId());
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
        model.addAttribute("all", personFacade.getAllPersons());
        return "/person/allPersons";
    }
}