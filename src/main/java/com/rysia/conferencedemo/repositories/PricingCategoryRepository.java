package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingCategoryRepository extends JpaRepository<PricingCategory, Long> {
}
