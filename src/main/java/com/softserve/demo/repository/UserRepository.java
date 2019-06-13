package com.softserve.demo.repository;

import com.softserve.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Integer id);

    Optional<User> findByUsername(String username);

    Optional<User> findByActivationCode(String activationCode);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
