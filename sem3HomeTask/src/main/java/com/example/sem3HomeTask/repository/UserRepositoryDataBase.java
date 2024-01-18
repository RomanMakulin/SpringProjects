package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryDataBase {
    private final JdbcTemplate jdbc;

    public UserRepositoryDataBase(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    public RowMapper<User> rowMapperUser(){
        return (r, i) -> {
            User rowObject = new User();
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
    }
}
