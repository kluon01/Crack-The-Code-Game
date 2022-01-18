package crackthecode.services;

import crackthecode.data.GameDao;
import crackthecode.data.RoundDao;
import crackthecode.models.Game;
import crackthecode.models.Round;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    public GameDao gameDao;
    public RoundDao roundDao;

    public GameService(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    public Game beginGame() {
        Game game = new Game();

        Integer[] arr = {1,2,3,4,5,6,7,8,9,0};
        List<Integer> listArr = Arrays.asList(arr);
        Collections.shuffle(listArr);
        game.setAnswer(""+ listArr.get(0) + listArr.get(1) + listArr.get(2) + listArr.get(3));
        game.setFinished(false);
        return gameDao.add(game);
    }

    public Round makeGuess(Round round) {
        Date date = new Date();
        round.setTime(new Timestamp(date.getTime()));
        String guess = round.getGuess();
        Game game = gameDao.findById(round.getGameId());
        String answer = game.getAnswer();
        int eCount = 0, pCount = 0;

        String result;

        for(int i = 0; i < answer.length(); i++) {
            if(guess.charAt(i) == (answer.charAt(i))) {
                eCount++;
            }
            else if(guess.indexOf(answer.charAt(i)) >= 0) {
                pCount++;
            }
        }
        //pCount -= eCount;
        result = "e:" + eCount + "p:" + pCount;
        if(eCount == 4) {
            game.setFinished(true);
            gameDao.update(game);
        }
        round.setResult(result);
        return roundDao.add(round);
    }

}
