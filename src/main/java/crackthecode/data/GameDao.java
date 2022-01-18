package crackthecode.data;

import crackthecode.models.Game;

import java.util.List;

public interface GameDao {
    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);

    boolean update(Game game);
}
