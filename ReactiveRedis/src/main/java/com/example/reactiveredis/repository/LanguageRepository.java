package com.example.reactiveredis.repository;

import com.example.reactiveredis.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class LanguageRepository {
      private ReactiveRedisOperations<Long, Language> template;

      @Autowired
      public LanguageRepository(ReactiveRedisOperations<Long, Language> template) {
            this.template = template;
      }

      public Flux<Language> findAll()  {
            return template
                  .<Long, Language> opsForHash()
                  .values()
      }
}
