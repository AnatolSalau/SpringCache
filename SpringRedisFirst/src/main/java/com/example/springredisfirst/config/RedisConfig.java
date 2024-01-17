package com.example.springredisfirst.config;

import com.example.springredisfirst.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
      @Bean
      public JedisConnectionFactory getConnectionFactory() {
            return new JedisConnectionFactory();
      }

      @Bean
      public RedisTemplate<String, Person> redisTemplate() {
            RedisTemplate<String, Person> redisTemplate = new RedisTemplate<String, Person>();
            redisTemplate.setConnectionFactory(getConnectionFactory());
            return redisTemplate;
      }
}
