package com.example.springredisfirst.controllers;

import com.example.springredisfirst.model.Person;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

      @Resource(name = "redisTemplate")
      private HashOperations<String,String, Person> hashOperations;

      @GetMapping("/{key}")
      public Person getPerson(@PathVariable("key") String key) {
            System.out.println("Going to get Person with id: " + key);
            return hashOperations.get("Person", key);
      }

      @PostMapping()
      public Person postPerson(@RequestBody Person person) {
            System.out.println("Going to set Person with id: ");
            System.out.println(person);
            hashOperations.put("Person",person.id(),person);

            return hashOperations.get("Person", person.id());
      }
}
