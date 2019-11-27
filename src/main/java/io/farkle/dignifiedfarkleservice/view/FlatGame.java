package io.farkle.dignifiedfarkleservice.view;

import java.util.Date;

public interface FlatGame {

  long getId();

  Date getCreated();

  int getPreferredNumPlayers();

  int getNumberOfRounds();

}
