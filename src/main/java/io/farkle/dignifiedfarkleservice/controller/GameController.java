package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.GameRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.Game.State;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import io.farkle.dignifiedfarkleservice.model.pojo.GamePreference;
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
@RequestMapping("games")
public class GameController {

  private final GameRepository repository;

  public GameController(GameRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Game> get() {
    return repository.getAllBy();
  }

  @PostMapping(value = "join", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Game join(@RequestBody GamePreference preference, Authentication authentication) {
    Player player = (Player) authentication.getPrincipal();
    Game game = repository.findFirstByStateAndPreferredNumPlayers(State.PENDING, preference.getNumPlayers())
        .orElseGet(() -> {
          Game g = new Game();
          g.setPreferredNumPlayers(preference.getNumPlayers());
          return repository.save(g);
        });
    for (GamePlayer gamePlayer : game.getGamePlayers()) {
      if (gamePlayer.getPlayer().getId() == player.getId()) {
        throw new IllegalArgumentException();
      }
    }
    GamePlayer gamePlayer = new GamePlayer();
    gamePlayer.setGame(game);
    gamePlayer.setPlayer(player);
    game.getGamePlayers().add(gamePlayer);
    // TODO If gamePlayers.size = preferredNumPlayers then start game.
    return repository.save(game);
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
    Player players = (Player) auth.getPrincipal();
    Game game = repository.findById(id).get();
    List<GamePlayer> gamePlayers = game.getGamePlayers();
    for (GamePlayer gamePlayer : gamePlayers) {
      if (gamePlayer.getPlayer().getId() == players.getId()) {
        return game;
      }
    }
    throw new NoSuchElementException();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public void notAllowed() {
  }

}
