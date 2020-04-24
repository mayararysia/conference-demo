package com.firstproject.conferencedemo.repositories;

import com.firstproject.conferencedemo.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
