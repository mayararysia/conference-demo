package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
