package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
