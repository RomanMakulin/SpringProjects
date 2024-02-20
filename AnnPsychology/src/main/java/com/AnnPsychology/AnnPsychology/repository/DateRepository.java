package com.AnnPsychology.AnnPsychology.repository;

import com.AnnPsychology.AnnPsychology.models.SessionDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<SessionDate, Long> {
}
