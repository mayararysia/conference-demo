package com.rysia.conferencedemo.repositories;

import com.rysia.conferencedemo.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
