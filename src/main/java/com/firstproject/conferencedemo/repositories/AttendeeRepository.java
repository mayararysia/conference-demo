package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
