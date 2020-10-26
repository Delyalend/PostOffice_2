package com.postalSystem.repository;

import com.postalSystem.model.PostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostItemRepo extends JpaRepository<PostItem, Long> {
}
