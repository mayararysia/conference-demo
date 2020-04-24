package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {
}
