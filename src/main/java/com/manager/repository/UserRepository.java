package com.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
  public Optional<User> findByUsernameIgnoreCase(String username);
}
