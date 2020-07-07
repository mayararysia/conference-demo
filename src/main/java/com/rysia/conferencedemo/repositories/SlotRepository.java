package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
