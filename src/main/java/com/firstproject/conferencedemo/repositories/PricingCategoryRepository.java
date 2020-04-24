package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingCategoryRepository extends JpaRepository<PricingCategory, Long> {
}
