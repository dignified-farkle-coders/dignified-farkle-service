package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action, Long> {

  List<Action> getAllBy();

  Optional<Action> findFirstByGameOrderByCreatedDesc(Game game);
}
