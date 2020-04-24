package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.SessionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionScheduleRepository extends JpaRepository<SessionSchedule, Long> {
}
