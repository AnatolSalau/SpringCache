package com.example.spring_default_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringDefaultCacheApplication {

      public static void main(String[] args) {
            SpringApplication.run(SpringDefaultCacheApplication.class, args);
      }

}
