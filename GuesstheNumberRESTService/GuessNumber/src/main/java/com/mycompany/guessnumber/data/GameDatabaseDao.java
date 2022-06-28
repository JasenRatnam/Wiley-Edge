
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Game;
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
 * DAO of game in the database
 * @author Jasen Ratnam
 */
@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao{

    //Using JDBC template
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * add a game object to the database
     * Uses only answer
     * Id, and status are auto added to default of false
     * @param game to add to database
     * @return game object with id assigned
     */
    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getAnswer());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    /**
     * get all games in the database as a list
     * @return list of game objects
     */
    @Override
    public List<Game> getAll() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    /**
     * get a game with matching ID
     * @param id of game wanted
     * @return game wanted with matching ID
     */
    @Override
    public Game findById(int id) {
        final String sql = "SELECT *"
                + "FROM game WHERE gameId = ?;";

        try{
            return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    /**
     * Update a game in the database with new information given
     * Use information in game object given
     * @param game with updated information
     * @return success of update game
     */
    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "status = ? "
                + "WHERE gameId= ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.isStatus(),
                game.getId()) > 0;
    }

    /**
     * delete a game with given id
     * @param id of game to delete
     * @return success of delete
     */
    @Override
    public boolean deleteById(int id) {
        final String sql1 = "DELETE FROM round WHERE gameId = ?;";
        final String sql2 = "DELETE FROM game WHERE gameId = ?;";
        
        jdbcTemplate.update(sql1, id);
        return jdbcTemplate.update(sql2, id) > 0;
    }
    
    /***
     * row mapper of Game object from database row to Game objects
     */
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("gameId"));
            game.setAnswer(rs.getInt("answer"));
            game.setStatus(rs.getBoolean("status"));
            return game;
        }
    }

}
