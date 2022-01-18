package crackthecode.data;

import crackthecode.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class GameDBDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public GameDBDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer, finished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            int val = game.isFinished() ? 1 : 0;
            statement.setString(2, "" + val);
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "SELECT * FROM game WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "finished = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.isFinished(),
                game.getId()) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }

}
