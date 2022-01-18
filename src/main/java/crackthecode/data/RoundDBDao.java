package crackthecode.data;

import crackthecode.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDBDao implements RoundDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDBDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round add(Round round) {
        // Todo: Implement adding rounds and guess result
        final String sql = "INSERT INTO round(gameId, time, result, guess) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, String.valueOf(round.getGameId()));
            statement.setString(2, round.getTime().toString());
            statement.setString(3, round.getResult());
            statement.setString(4, round.getGuess());

            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Round> getAllByGameId(int gameId) {
        final String sql = "SELECT * FROM round WHERE gameId = ? ORDER BY time DESC;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }
/*
    @Override
    public boolean update(Round round) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
*/
    public static final class RoundMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("id"));
            round.setGameId(rs.getInt("gameId"));
            round.setTime(rs.getTimestamp("time"));
            round.setResult(rs.getString("result"));
            round.setGuess(rs.getString("guess"));
            return round;
        }
    }
}
