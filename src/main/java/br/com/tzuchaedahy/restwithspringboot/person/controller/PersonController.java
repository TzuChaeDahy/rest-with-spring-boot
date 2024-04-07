package br.com.tzuchaedahy.restwithspringboot.person.controller;

import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import br.com.tzuchaedahy.restwithspringboot.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findByID(@PathVariable(value = "id") long id) {
        return personService.findByID(id);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public long update(@RequestBody Person person) {
        return personService.update(person);
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE
    )
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }
}
