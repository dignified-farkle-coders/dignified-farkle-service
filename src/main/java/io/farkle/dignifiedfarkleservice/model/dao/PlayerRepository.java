package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Player;
import io.farkle.dignifiedfarkleservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

  Optional<Player> getPlayerByOauthKey(String oauthKey);

}


