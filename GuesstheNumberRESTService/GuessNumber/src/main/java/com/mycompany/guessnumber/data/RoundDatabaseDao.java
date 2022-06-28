
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * DAO of round in the database
 * @author Jasen Ratnam
 */
@Repository
@Profile("database")
public class RoundDatabaseDao implements RoundDao{

    //Using JDBC template
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * add a round object to the database
     * @param round to add to database
     * Use guess, result and gameId
     * id and timestamp is auto added
     * @return round object with timestamp and ID
     */
    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO round(guess, result, gameId) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGuess());
            statement.setString(2, round.getResult());
            statement.setInt(3, round.getGameId());
            return statement;

        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());
        round.setTime(findById(keyHolder.getKey().intValue()).getTime());

        return round;
    }

    /**
     * get all the rounds in the database ordered by time
     * @return list of round objects
     */
    @Override
    public List<Round> getAll() {
        final String sql = "SELECT * FROM round  ORDER BY time;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    /**
     * find a round based on a id given
     * @param id id of round wanted
     * @return the round mathcing the id
     */
    @Override
    public Round findById(int id) {
        final String sql = "SELECT * "
                + "FROM round WHERE roundId = ?;";

        try{
            return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    /**
     * update a round in the database
     * Use information in round object given
     * @param round with updated information
     * @return succes of update
     */
    @Override
    public boolean update(Round round) {
        final String sql = "UPDATE round SET "
                + "guess = ?, "
                + "time = ?, "
                + "result = ? "
                + "WHERE roundId = ?;";

        return jdbcTemplate.update(sql,
                round.getGuess(),
                round.getTime(),
                round.getResult(),
                round.getId()) > 0;
    }

    /**
     * delete a round with given id
     * @param id of round to delete
     * @return success of delete
     */
    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM round WHERE roundId = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    /***
     * row mapper of round object from database row to round objects
     */
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("roundId"));
            round.setGuess(rs.getInt("guess"));
            round.setTime(rs.getTimestamp("time").toLocalDateTime());
            round.setResult(rs.getString("result"));
            round.setGameId(rs.getInt("gameId"));
            return round;
        }
    }

}
