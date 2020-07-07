package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
}
