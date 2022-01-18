package crackthecode.controllers;

import crackthecode.models.Game;
import crackthecode.models.Round;
import crackthecode.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crackthecode")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game beginGame() {
        return gameService.beginGame();
    }

    // Makes a guess by passing the guess and gameId in as JSON.
    // The program must calculate the results of the guess and mark the game finished if the guess is correct.
    // It returns the Round object with the results filled in.
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeGuess(@RequestBody Round round) {
        return gameService.makeGuess(round);
    }

    // Returns a list of all games. Be sure in-progress games do not display their answer.
    @GetMapping("/game")
    public List<Game> allGames() {
        return gameService.gameDao.getAll();
    }

    // Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{gameId}")
    public Game findGameById(@PathVariable int gameId) {
        Game game = gameService.gameDao.findById(gameId);
        if(!(game.isFinished())) {
            game.setAnswer("Game is still in progress, no cheating");
        }
        return game;
    }

    // Returns a list of rounds for the specified game sorted by time.
    @GetMapping("/rounds/{gameId}")
    public List<Round> findRoundsbyGameId(@PathVariable int gameId) {
        return gameService.roundDao.getAllByGameId(gameId);
    }
}
