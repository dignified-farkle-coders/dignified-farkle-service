package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> getUserByOauthKey(String oauthKey);

}


