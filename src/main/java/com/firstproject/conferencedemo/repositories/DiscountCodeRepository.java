package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
}
