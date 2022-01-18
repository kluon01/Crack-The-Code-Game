package crackthecode.data;

import crackthecode.TestApplicationConfiguration;
import crackthecode.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDBDaoTest {

    @Autowired
    GameDao gameDao;

    @Test
    public void add() {
        Game game = new Game();
        game.setId(1);
        game.setAnswer("1234");
        game.setFinished(false);

        game = gameDao.add(game);

        Game fromDao = gameDao.findById(game.getId());

        assertEquals(game, fromDao);
    }

}