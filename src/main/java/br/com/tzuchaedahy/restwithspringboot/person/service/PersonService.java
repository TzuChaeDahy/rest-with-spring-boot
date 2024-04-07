package br.com.tzuchaedahy.restwithspringboot.person.service;

import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    Logger logger = Logger.getLogger(PersonService.class.getName());
    public Person findByID(long id) {
        logger.info(String.format("finding person! - id %d", id));

        // call repository

        Person person = mockPerson(1);

        return person;
    }

    public List<Person> findAll() {
        logger.info("finding all people!");

        // call repository

        List<Person> persons = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            Person person = mockPerson(i);

            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        // call repository

        logger.info("creating a person!");

        return person;
    }

    public long update(Person person) {
        // call repository

        logger.info("updating a person!");

        return person.getId();
    }

    public void delete(long id) {
        // call repository
        logger.info(String.format("deleting a person! id %s", id));
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(new AtomicLong().get());
        person.setFirstName("name " + i);
        person.setLastName("surname " + i);
        person.setAge((int) Math.floor(Math.random() * 80) + 1);

        return person;
    }
}
