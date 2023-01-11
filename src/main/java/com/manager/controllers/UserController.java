package com.manager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.model.User;
import com.manager.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<User> users() {
    return this.userRepository.findAll();
  }

  @GetMapping("/{username}")
  public ResponseEntity<User> user(@PathVariable String username) {
    Optional<User> user = this.userRepository.findByUsernameIgnoreCase(username);

    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(user.get());
  }

  @PostMapping
  public ResponseEntity<User> user(@RequestBody User user) {
    this.userRepository.save(user);
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<User> delete(@PathVariable String username) {
    Optional<User> user = this.userRepository.findByUsernameIgnoreCase(username);

    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    this.userRepository.delete(user.get());
    return ResponseEntity.noContent().build();
  }
}
