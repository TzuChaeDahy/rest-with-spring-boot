package br.com.tzuchaedahy.restwithspringboot.person.repository;

import br.com.tzuchaedahy.restwithspringboot.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
