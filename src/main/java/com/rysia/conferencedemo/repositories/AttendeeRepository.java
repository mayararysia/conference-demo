package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
