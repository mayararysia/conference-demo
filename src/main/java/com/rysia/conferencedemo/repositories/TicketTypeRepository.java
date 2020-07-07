package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
