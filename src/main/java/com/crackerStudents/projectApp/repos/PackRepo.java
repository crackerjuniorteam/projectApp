package com.crackerStudents.projectApp.repos;

import com.crackerStudents.projectApp.domain.Pack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackRepo extends JpaRepository<Pack, UUID> {
    Pack findByName(String name);

    Page<Pack> findAll(Pageable pageable);

    Page<Pack> findByAuthorId(Pageable pageable, UUID id);

    Page<Pack> findByIsPublicTrue(Pageable pageable);
}
