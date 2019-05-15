package com.softserve.demo.repository;

import com.softserve.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Integer id);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
