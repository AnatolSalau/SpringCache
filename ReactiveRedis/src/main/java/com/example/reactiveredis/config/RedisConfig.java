package com.example.reactiveredis.config;

import com.example.reactiveredis.model.Language;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisConfig {

      @Value("${spring.data.redis.host}")
      String HOST;

      @Value("${spring.data.redis.port}")
      Integer PORT;

      @Bean
      public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
            return new LettuceConnectionFactory(HOST, PORT);
      }

      @Bean
      public ReactiveRedisTemplate<Long, Language> reactiveRedisTemplateJSON(ReactiveRedisConnectionFactory connectionFactory) {
            RedisSerializationContext<Long, Language> serializationContext = RedisSerializationContext
                  .<Long, Language>newSerializationContext(new StringRedisSerializer())
                  .hashKey(new StringRedisSerializer())
                  .hashValue(new Jackson2JsonRedisSerializer<>(Language.class))
                  .build();
            return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
      }


}
