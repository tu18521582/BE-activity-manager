package com.kmstechnology.activitycrud.repository;

import com.kmstechnology.activitycrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmailOrUsername(String email, String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}