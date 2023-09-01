package com.spring.boot.data.mongodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@Slf4j
@SpringBootTest
class SpringBootDataMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        Person p = new Person("Joe", 34);

        // Insert is used to initially store the object into the database.
        mongoTemplate.insert(p);
        log.info("Insert: " + p);

        // Find
        p = mongoTemplate.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        List<Person> personList = mongoTemplate.find(new Query(Criteria.where("name").is("Joe")), Person.class);
        log.info("Found: " + personList);

        // Update
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("Joe")), Update.update("age", 35), Person.class);
        p = mongoTemplate.findOne(Query.query(Criteria.where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

        // Delete
        mongoTemplate.remove(p);

        // Check that deletion worked
        List<Person> people = mongoTemplate.findAll(Person.class);
        log.info("Number of people = : " + people.size());

        mongoTemplate.dropCollection(Person.class);
    }

}
