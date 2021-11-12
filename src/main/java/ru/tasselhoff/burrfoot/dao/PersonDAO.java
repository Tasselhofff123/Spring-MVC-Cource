package ru.tasselhoff.burrfoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tasselhoff.burrfoot.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int PEOPLE_COUNT;



    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

        //  Person personToBeUpdated = show(id);
      //  personToBeUpdated.setName(updatedPerson.getName());
      //  personToBeUpdated.setAge(updatedPerson.getAge());
       // personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
            jdbcTemplate.update("DELETE from Person WHERE id=?", id);
        // people.removeIf(p -> p.getId() == id);
    }
}
