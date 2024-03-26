package com.AnnPsychology.AnnPsychology.repository;

import com.AnnPsychology.AnnPsychology.models.SessionDate;
import com.AnnPsychology.AnnPsychology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DateRepository extends JpaRepository<SessionDate, Long> {
    SessionDate getBySessionDate(LocalDateTime sessionDate);
}
