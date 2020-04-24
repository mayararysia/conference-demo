package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
