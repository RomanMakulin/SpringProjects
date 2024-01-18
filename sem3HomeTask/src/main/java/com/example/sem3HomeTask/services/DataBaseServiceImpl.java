package com.example.sem3HomeTask.services;
import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepositoryDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseServiceImpl implements DataBaseService{
    @Autowired
    private UserRepositoryDataBase userRepositoryDataBase;
    public List<User> findAllFromDB() {
        String sql = "SELECT * FROM userTable";
        return userRepositoryDataBase.getJdbc().query(sql, userRepositoryDataBase.rowMapperUser());
    }

    public User createToDB(User user) {
        String sql = "INSERT INTO userTable VALUES (?, ?, ?)";
        userRepositoryDataBase.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail());
        return user;
    }

    public List<User> filterByAgeDB(int age) {
        String sql = "SELECT * FROM userTable WHERE age >= " + age;
        return userRepositoryDataBase.getJdbc().query(sql, userRepositoryDataBase.rowMapperUser());
    }

    public List<User> sortByAgeDB() {
        String sql = "SELECT * FROM userTable\n" +
                " ORDER BY Age;";
        return userRepositoryDataBase.getJdbc().query(sql, userRepositoryDataBase.rowMapperUser());
    }
}
