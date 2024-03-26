package com.AnnPsychology.AnnPsychology.repository;

import com.AnnPsychology.AnnPsychology.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
