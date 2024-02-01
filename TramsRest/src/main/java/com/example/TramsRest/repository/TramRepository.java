package com.example.TramsRest.repository;

import com.example.TramsRest.models.Tram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интферфейс взаимодействия, управления БД
 */
@Repository
public interface TramRepository extends JpaRepository<Tram, Integer> {
}
