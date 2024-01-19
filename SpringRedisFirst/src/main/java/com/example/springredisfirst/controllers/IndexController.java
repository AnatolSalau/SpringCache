package com.example.springredisfirst.controllers;

import com.example.springredisfirst.model.Person;
import com.example.springredisfirst.services.PersonRedisService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

      final PersonRedisService personRedisService;

      public IndexController(PersonRedisService personRedisService) {
            this.personRedisService = personRedisService;
      }

      @GetMapping("/{key}")
      public Person getPerson(@PathVariable("key") String key) {
            Person person = personRedisService.getPerson(key);
            return person;
      }

      @PostMapping()
      public Person postPerson(@RequestBody Person person) {
            Person saved = personRedisService.savePerson(person);
            return saved;
      }
}
