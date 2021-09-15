package com.kmstechnology.activitycrud.repository;

import com.kmstechnology.activitycrud.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findActivityById(Long id);
}
