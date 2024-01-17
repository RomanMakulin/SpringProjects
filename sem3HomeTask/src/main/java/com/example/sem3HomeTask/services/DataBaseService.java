package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseService {
    @Autowired
    private UserRepository2 userRepository2;
    public List<User> findAllFromDB() {
        String sql = "SELECT * FROM userTable";
        return userRepository2.getJdbc().query(sql, userRepository2.rowMapperUser());
    }

    public User createToDB(User user) {
        String sql = "INSERT INTO userTable VALUES (?, ?, ?)";
        userRepository2.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail());
        return user;
    }

    public List<User> filterByAgeDB(int age) {
        String sql = "SELECT * FROM userTable WHERE age > " + age;
        return userRepository2.getJdbc().query(sql, userRepository2.rowMapperUser());
    }

    public List<User> sortByAgeDB() {
        String sql = "SELECT * FROM userTable\n" +
                " ORDER BY Age;";
        return userRepository2.getJdbc().query(sql, userRepository2.rowMapperUser());
    }
}
