package crackthecode.data;

import crackthecode.models.Round;

import java.util.List;

public interface RoundDao {
    Round add(Round round);

    List<Round> getAllByGameId(int gameId);
    /*
    // true if item exists and is updated
    boolean update(Round round);

    // true if item exists and is deleted
    boolean deleteById(int id);
    */
}

