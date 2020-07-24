package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingCategoryRepository extends JpaRepository<PricingCategory, Long> {
}
