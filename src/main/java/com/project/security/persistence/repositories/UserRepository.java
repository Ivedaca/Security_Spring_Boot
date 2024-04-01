package com.project.security.persistence.repositories;

import com.project.security.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //find users by email
    @Query(value = "SELECT * FROM  user WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

}
