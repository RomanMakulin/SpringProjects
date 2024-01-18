package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.DataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    DataBaseRepository dataBaseRepository;

    @Autowired
    NotificationService notificationService;

    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO userTable VALUES (?, ?, ?)";
        dataBaseRepository.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail());
        notificationService.notifyUser(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM userTable";
        return dataBaseRepository.getJdbc().query(sql, dataBaseRepository.rowMapperUser());
    }
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE userTable\n" +
                " SET firstName = ?, lastName = ? \n" +
                " WHERE id = ?;";
        dataBaseRepository.getJdbc().update(sql, user.getFirstName(), user.getAge(), user.getEmail());
    }

    @Override
    public void delUser(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        dataBaseRepository.getJdbc().update(sql, id);
    }
}
