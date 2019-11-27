package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.Game.State;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

  List<Game> getAllBy();

//  Optional<Game> findFirstByGameOrderByCreatedDesc(Game game);

  Optional<Game> findFirstByStateAndPreferredNumPlayers(State state, int preferredNumPlayers);
}
