package com.example.LoginProfile.services.db;

import com.example.LoginProfile.models.User;
import com.example.LoginProfile.repository.DB;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UsersManageServiceImpl implements UsersManageService {
    private DB db;
    @Override
    public List<User> getAll() {
        return db.getJdbc().query("SELECT * FROM userTable", db.rowMapperUser());
    }

    @Override
    public User getUserByID() {
        return null;
    }

    @Override
    public void createUser(User user) {
        db.getJdbc().update("INSERT INTO userTable VALUES (DEFAULT, ?, ?, ?)", user.getLogin(),
                user.getEmail(), user.getPassword());
    }

    @Override
    public void updateUser(User user) {
        db.getJdbc().update("UPDATE userTable SET login=?, email=?, password=?, isAdmin=? WHERE id=?",
                user.getLogin(),
                user.getEmail(),
                user.getPassword(),
                user.isAdmin(),
                user.getId());
    }

    @Override
    public void deleteUser(User user) {
        db.getJdbc().update("");
    }
}
