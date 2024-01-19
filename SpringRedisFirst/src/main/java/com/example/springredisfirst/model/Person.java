package com.example.springredisfirst.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("personCache")
public record Person(String name, String id) implements Serializable {
}
