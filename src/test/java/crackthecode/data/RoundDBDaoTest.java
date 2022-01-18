package crackthecode.data;

import crackthecode.TestApplicationConfiguration;
import crackthecode.models.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDBDaoTest {

    @Autowired
    RoundDao roundDao;

    @Test
    public void add() {
        Round round = new Round();
        round.setId(1);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        round.setTime(time);
        round.setGuess("1234");
        round.setGameId(1);
        round.setResult("e:4p:0");

        round = roundDao.add(round);

        List<Round> fromDao = roundDao.getAllByGameId(round.getGameId());

        Round roundFromDao = fromDao.get(fromDao.size() - 1);

        assertEquals(round, roundFromDao);
    }
}