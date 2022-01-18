package crackthecode.services;

import crackthecode.TestApplicationConfiguration;
import crackthecode.data.GameDao;
import crackthecode.data.RoundDao;
import crackthecode.models.Game;
import crackthecode.models.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameServiceTest {

    @Autowired
    GameService gameService;
    @Autowired
    GameDao gameDao;

    Round firstRound;
    Round exactRound;

    @Before
    public void setUp() {
        firstRound = new Round();
        firstRound.setId(1);
        firstRound.setGameId(1);
        firstRound.setGuess("4321");
        firstRound.setTime(new Timestamp(System.currentTimeMillis()));
        firstRound.setResult(null);

        exactRound = new Round();
        exactRound.setId(1);
        exactRound.setGameId(1);
        exactRound.setGuess("1234");
        exactRound.setTime(new Timestamp(System.currentTimeMillis()));
        exactRound.setResult(null);
    }

    @Test
    public void beginGame() {
        Game game = gameService.beginGame();
        assertTrue(gameDao.findById(game.getId()).equals(game));
    }

    @Test
    public void makeGuess() {
        Round round = new Round();
        round = gameService.makeGuess(firstRound);
        assertTrue(round.getResult().charAt(2) == '0');

        round = gameService.makeGuess(exactRound);
        assertTrue(round.getResult().charAt(2) == '4');
    }
}