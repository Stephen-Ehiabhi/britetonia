package com.britetonia.repository;

import com.britetonia.model.User0model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User0model, Long> {
    boolean existsByName(String name);
}
