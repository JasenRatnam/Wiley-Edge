
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public class RoomDaoDB implements RoomDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Room> getAllRooms() {
        //declaring our query as a String.
        final String SELECT_ALL_ROOMS = "SELECT * FROM room";
        // return the results of our "query" method 
        return jdbc.query(SELECT_ALL_ROOMS, new RoomMapper());
    }

    @Override
    public Room getRoomById(int id) {
        //"queryForObject" will throw an Exception if no object is returned from the query.
        try {
            final String SELECT_ROOM_BY_ID = "SELECT * FROM room WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROOM_BY_ID, new RoomMapper(), id);
        } catch(DataAccessException ex) {
            //return null in the catch 
            //indicator that we could not retrieve the object.
            return null;
        }
    }

    /**
     * return a Room object, 
     * the Room we pass in does not have an ID 
     * the one returned will have the ID that the database assigns it. 
     * @Transactional:
     * Every query run against the database in this method is part of a single 
     * transaction, 
     * If any of the queries fail, the program will roll back all of them. 
     * @param room
     * @return 
     */
    @Override
    @Transactional
    public Room addRoom(Room room) {
        //use the "update" method to run it with the necessary data.
        final String INSERT_ROOM = "INSERT INTO room(name, description) VALUES(?,?)";
        jdbc.update(INSERT_ROOM, 
                room.getName(), 
                room.getDescription());
        
        //special query to get the ID from the database
        //LAST_INSERT_ID() is a function built into MySQL that returns the ID 
        //of the most recent row inserted into the database.
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        //put that ID into the Room object and return Room.
        room.setId(newId);
        return room;
    }

    /**
     * define our UPDATE query  
     * use the "update" method to send it to the database with the necessary data.
     * @param room 
     */
    @Override
    public void updateRoom(Room room) {
        final String UPDATE_ROOM = "UPDATE room SET name = ?, description = ? WHERE id = ?";
        jdbc.update(UPDATE_ROOM,
                room.getName(),
                room.getDescription(),
                room.getId());
    }

    @Override
    @Transactional
    public void deleteRoomById(int id) {
        final String DELETE_MEETING_EMPLOYEE_BY_ROOM = "DELETE me.* FROM meeting_employee me "
                + "JOIN meeting m ON me.meetingId = m.id WHERE m.roomId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE_BY_ROOM, id);
        
        final String DELETE_MEETING_BY_ROOM = "DELETE FROM meeting WHERE roomId = ?";
        jdbc.update(DELETE_MEETING_BY_ROOM, id);
        
        final String DELETE_ROOM = "DELETE FROM room WHERE id = ?";
        jdbc.update(DELETE_ROOM, id);
    }
    
    /**
     * RoomMapper so we can turn the database data into a Room object
     * Can access this mapper to add a Room object to the Meeting objects 
     * when we implement the Meeting DAO.
     */
    public static final class RoomMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int index) throws SQLException {
            Room rm = new Room();
            rm.setId(rs.getInt("id"));
            rm.setName(rs.getString("name"));
            rm.setDescription(rs.getString("description"));
            return rm;
        }
    }
    
}