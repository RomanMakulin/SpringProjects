package com.example.sem3HomeTask.services.db;

import com.example.sem3HomeTask.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class RowMapperService {
    /**
     * Формирование маппера (строкового) для данных из БД
     *
     * @return сформированный объект юзера на основе БД
     */
    public RowMapper<User> rowMapperUser() {
        return (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setPassword(r.getString("password"));
            return rowObject;
        };
    }
}