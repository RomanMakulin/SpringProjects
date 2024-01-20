package com.example.sem3HomeTask.repository;

import com.example.sem3HomeTask.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Repository
public class DataBaseRepository {
    /**
     * Хранилище данных - База Данных
     */
    private final JdbcTemplate jdbc;
}
