package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.ActionRepository;
import io.farkle.dignifiedfarkleservice.model.dao.GameRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
import io.farkle.dignifiedfarkleservice.model.entity.Players;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

  private final GameRepository repository;

  public GameController(GameRepository repository) {
    this.repository = repository;
  }



  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Game post(@RequestBody Game game) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(game);
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Game get(@PathVariable long id, Authentication auth) {
    Players players = (Players) auth.getPrincipal();
    Game game = repository.findById(id).get();
    List<GamePlayer> gamePlayers = game.getGamePlayers();
    for (GamePlayer gamePlayer : gamePlayers) {
      if (gamePlayer.getId() == players.getId()) {
        return game;
      }
    }
    throw new NoSuchElementException();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
