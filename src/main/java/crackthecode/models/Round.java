package crackthecode.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Round {

    private int id;
    private int gameId;
    private Timestamp time;
    private String result;
    private String guess;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getGuess(){return guess;}
    public void setGuess(String guess){this.guess = guess;}

    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time){
        this.time = time;
    }

    public int getGameId(){
        return gameId;
    }
    public void setGameId(int gameId){
        this.gameId = gameId;
    }

    public String getResult(){return result;}
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && gameId == round.gameId && Objects.equals(time, round.time) && Objects.equals(result, round.result) && guess.equals(round.guess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, time, result, guess);
    }
}
