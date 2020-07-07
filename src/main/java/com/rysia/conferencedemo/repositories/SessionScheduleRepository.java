package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.SessionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionScheduleRepository extends JpaRepository<SessionSchedule, Long> {
}
