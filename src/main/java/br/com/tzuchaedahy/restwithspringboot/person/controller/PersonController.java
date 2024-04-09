package br.com.tzuchaedahy.restwithspringboot.person.controller;

import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import br.com.tzuchaedahy.restwithspringboot.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> findByID(@PathVariable(value = "id") long id) {
        Person person = personService.findByID(id);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personService.findAll();

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> create(@RequestBody @Validated Person person) {
        Long id = personService.create(person);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> update(@RequestBody Person person) {
        Long id =  personService.update(person);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
