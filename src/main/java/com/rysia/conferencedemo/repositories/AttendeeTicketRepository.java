package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.AttendeeTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Long> {
}
