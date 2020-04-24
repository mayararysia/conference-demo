package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.AttendeeTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Long> {
}
