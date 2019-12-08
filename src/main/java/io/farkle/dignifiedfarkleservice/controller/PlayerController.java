package io.farkle.dignifiedfarkleservice.controller;

import io.farkle.dignifiedfarkleservice.model.dao.PlayerRepository;
import io.farkle.dignifiedfarkleservice.model.entity.Action;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@Api(value = "PlayerControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

  private final PlayerRepository repository;

  public PlayerController(PlayerRepository repository) {
    this.repository = repository;
  }

  @ApiOperation("players")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Player.class)})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Player> get(){
    return repository.getAllBy();
  }


  @ApiOperation(" ")
  @ApiResponses( value =  {@ApiResponse(code = 200, message = "Ok" , response = Player.class)})
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Player post(@RequestBody Player player) {
    // TODO Validation
    // TODO Execute game logic
    return repository.save(player);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
