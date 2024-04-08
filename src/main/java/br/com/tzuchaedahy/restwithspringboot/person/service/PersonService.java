package br.com.tzuchaedahy.restwithspringboot.person.service;

import br.com.tzuchaedahy.restwithspringboot.exceptions.ResourceNotFoundException;
import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import br.com.tzuchaedahy.restwithspringboot.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public Person findByID(Long id) {
        logger.info(String.format("finding person! - id %d", id));

        Person person = personRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("no records found for this id!"));

        return person;
    }

    public List<Person> findAll() {
        logger.info("finding all people!");

        List<Person> persons = personRepository.findAll();

        return persons;
    }

    public Long create(Person person) {
        logger.info("creating a person!");

        Person savedPerson = personRepository.save(person);

        return savedPerson.getId();
    }

    public Long update(Person person) {
        logger.info("updating a person!");

        Person oldPerson = personRepository.findById(person.getId()).
                orElseThrow(() -> new ResourceNotFoundException("no records found for this id!"));

        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        oldPerson.setAge(person.getAge());

        return personRepository.save(oldPerson).getId();
    }

    public void delete(Long id) {
        logger.info(String.format("deleting a person! id %s", id));

        boolean exists = personRepository.existsById(id);

        if(exists) {
            personRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("no records found for this id!!");
        }
    }
}
