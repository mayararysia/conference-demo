package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {
}
