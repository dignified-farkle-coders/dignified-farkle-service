package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface GamePlayerRepository extends CrudRepository<GamePlayer, Long> {

  List<GamePlayer> getAllBy();

  Optional<GamePlayer> findFirstByGameOrderByCreatedDesc(Game game);
}
