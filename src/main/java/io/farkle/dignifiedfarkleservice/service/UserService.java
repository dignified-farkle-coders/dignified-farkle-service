package io.farkle.dignifiedfarkleservice.service;

import io.farkle.dignifiedfarkleservice.model.dao.UserRepository;
import io.farkle.dignifiedfarkleservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  @Autowired
  private UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User getOrCreateUser(String oauthKey) {
    return repository.getUserByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          return repository.save(user);
        });
  }
}