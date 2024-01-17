package com.example.spring_default_cache.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class SimpleCacheCustomizer
      implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

      @Override
      public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setCacheNames(asList("newCache1", "newCache2"));
      }
}
