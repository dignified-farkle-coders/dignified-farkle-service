package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.GameRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.Game.State;
import io.farkle.dignifiedfarkleservice.model.entity.GamePlayer;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import io.farkle.dignifiedfarkleservice.model.pojo.GamePreference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

  @GetMapping(value = "{id:\\d+}/action", produces = MediaType.APPLICATION_JSON_VALUE)
  public Action getLastAction(@PathVariable long id, Authentication authentication) {
    List<Action> actions = get(id, authentication).getActions();
    return actions.get(actions.size() - 1);
  }

  @PostMapping(value = "join", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Game join(@RequestBody GamePreference preference, Authentication authentication) {
    Player player = (Player) authentication.getPrincipal();
    Game game = repository
        .findFirstByStateAndPreferredNumPlayers(State.PENDING, preference.getNumPlayers())
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

//    action.setGame(game);
//    action.setAvailableDice(new int[]{1, 2, 3});
//    action.setFrozenDice(new int[]{2, 2, 2});
//    action.setGame(game);

    GamePlayer gamePlayer = new GamePlayer();
    gamePlayer.setGame(game);
    gamePlayer.setPlayer(player);
    game.getGamePlayers().add(gamePlayer);
    if (game.getGamePlayers().size() == preference.getNumPlayers()) {
      Action action = new Action();
      action.setGame(game);
      action.setAvailableDice(new int[]{1, 2, 3, 4, 5, 6}); // throw random
      action.setNextPlayer(game.getGamePlayers().get(0).getPlayer());
      game.getActions().add(action);
      // This line breaks the program but I feel like I need it.
      //ERROR: Could not commit JPA transaction; nested exception is javax.
//      game.getActions().(actions);
//      System.out.println("GameID: " + action.getGame().getId());
//      System.out.println("Display Name: " + action.getPlayer().getDisplayName());
//      System.out.println(Arrays.toString(action.getAvailableDice()));
      // TODO If gamePlayers.size = preferredNumPlayers then start game.
      game.setState(State.IN_PROGRESS);
      game.setYourTurn(game.getLastAction().getNextPlayer().getId() == player.getId());
      System.out.println("GamePlayerID: " + gamePlayer.getId());
      System.out.println();

    }
    return repository.save(game);
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Game get(@PathVariable long id, Authentication auth) {
    Player player = (Player) auth.getPrincipal();
    Game game = repository.findById(id).get();
    List<GamePlayer> gamePlayers = game.getGamePlayers();
    for (GamePlayer gamePlayer : gamePlayers) {
      if (gamePlayer.getPlayer().getId() == player.getId()) {
        game.setYourTurn(game.getLastAction().getNextPlayer().getId() == player.getId());
        return game;
      }
    }
    throw new NoSuchElementException();
  }

  @PostMapping(value = "{id:\\d+}/actions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Game post(@PathVariable long id, @RequestBody Action action, Authentication auth) {
    Player player = (Player) auth.getPrincipal();
    Game game = get(id, auth);
    if (!game.isYourTurn()) {
      throw new IllegalArgumentException();
    }
    // TODO Validate and Process
    int turn = game.getLastAction().getTurn() + 1;
    action.setTurn(turn);
    action.setGame(game);
    action.setPlayer(player);
    game.getActions().add(action);
    return repository.save(game);

  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(IllegalArgumentException.class)
//  public void notAllowed() {
//  }

}
