package com.example.springredisfirst.services;

import com.example.springredisfirst.model.Person;
import com.example.springredisfirst.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonRedisService {
      final PersonRepository personRepository;

      public PersonRedisService(PersonRepository personRepository) {
            this.personRepository = personRepository;
      }

      public Person savePerson(Person person) {
            Person save = personRepository.save(person);
            return save;
      }
      public Person getPerson(String id) {
            Optional<Person> fromDb = personRepository.findById(id);
            return fromDb.get();
      }
}
