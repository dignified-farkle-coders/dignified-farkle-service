package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Players;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Players, Long> {

  List<Players> getAllBy();

  Optional<Players> getPlayerByOauthKey(String oauthKey);

}


