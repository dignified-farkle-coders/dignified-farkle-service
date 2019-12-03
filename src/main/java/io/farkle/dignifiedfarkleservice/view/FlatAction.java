package io.farkle.dignifiedfarkleservice.view;

import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import java.util.Date;
import org.springframework.lang.NonNull;

public interface FlatAction {

  long getActionId();

  Date getCreated();

  Game getGame();

  Player getPlayer();

  Player getNextPlayer();

  int[] getAvailableDice();

  int[] getFrozenDice();

}
