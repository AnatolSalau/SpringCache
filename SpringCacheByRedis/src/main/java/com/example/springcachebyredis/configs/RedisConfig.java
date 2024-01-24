package com.example.springcachebyredis.configs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig implements CachingConfigurer  {
      @Value("${spring.data.redis.host}")
      private String redisHost;

      @Value("${spring.data.redis.port}")
      private int redisPort;

      @Bean
      public RedisStandaloneConfiguration redisStandaloneConfiguration() {
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
            configuration.setHostName(redisHost);
            configuration.setPort(redisPort);
            return configuration;
      }

      @Bean
      public JedisConnectionFactory getConnectionFactory() {
            return new JedisConnectionFactory(redisStandaloneConfiguration());
      }

      @Bean
      public RedisTemplate<String, Object> redisTemplate() {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(getConnectionFactory());
            // key
            template.setKeySerializer(this.objectJackson2JsonRedisSerializer());
            // value
            template.setValueSerializer(this.objectJackson2JsonRedisSerializer());
            // value hashmap
            template.setHashValueSerializer(this.objectJackson2JsonRedisSerializer());
            return template;
      }

      @Bean
      public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
            RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                  .entryTtl(Duration.ofMinutes(1))
                  .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                              redisSerializer
                        )
                  )
                  .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                              this.objectJackson2JsonRedisSerializer()
                        )
                  )
                  .disableCachingNullValues();
            RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                  .cacheDefaults(configuration)
                  .build();
            return cacheManager;
      }

      public Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper
                  .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                  .activateDefaultTyping(
                        LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.EVERYTHING,
                        JsonTypeInfo.As.WRAPPER_ARRAY
                  );
            Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(objectMapper,
                  Object.class
            );
            return serializer;
      }
}
