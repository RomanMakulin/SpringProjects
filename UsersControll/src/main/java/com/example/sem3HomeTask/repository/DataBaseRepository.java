package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseRepository {
    /**
     * Хранилище данных - База Данных
     */
    private final JdbcTemplate jdbc;

    /**
     * Конструктор. Можно использовать Autowired
     *
     * @param jdbc специальный класс БД
     */
    public DataBaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    /**
     * Формирование маппера (стрко) для данных из БД
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
            return rowObject;
        };
    }

}
