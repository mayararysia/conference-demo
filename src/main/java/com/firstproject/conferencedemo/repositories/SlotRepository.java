package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
