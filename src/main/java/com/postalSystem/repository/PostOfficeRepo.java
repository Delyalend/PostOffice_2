package com.postalSystem.repository;

import com.postalSystem.model.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostOfficeRepo extends JpaRepository<PostOffice,Long> {
    Optional<PostOffice> findByIndex(int index);
}
