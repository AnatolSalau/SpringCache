package com.example.springredisfirst.model;

import java.io.Serializable;

public record Person(String name, String id) implements Serializable {
}
