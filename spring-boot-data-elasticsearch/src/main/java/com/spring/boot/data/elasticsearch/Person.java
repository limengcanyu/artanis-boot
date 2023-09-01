package com.spring.boot.data.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Map;

@Document(indexName = "person")
@Data
public class Person {
    @Id
    String id;
    String firstname;
    String lastname;

    List<Person> friends;

    Map<String, Address> knownLocations;
}
