package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.AttendeeTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Long> {
}
