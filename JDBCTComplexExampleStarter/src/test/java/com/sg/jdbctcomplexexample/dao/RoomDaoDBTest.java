/*
 * Copyright Jasen Ratnam
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Jasen Ratnam
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoomDaoDBTest {
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    MeetingDao meetingDao;
    
    public RoomDaoDBTest() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Room> rooms = roomDao.getAllRooms();
        for(Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }
        
        List<Employee> employees = employeeDao.getAllEmployees();
        for(Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }
        
        List<Meeting> meetings = meetingDao.getAllMeetings();
        for(Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }
    
    /**
     * Test of addRoom and getRoomById method, of class RoomDaoDB.
     * These tests almost always end up being the exact same test
     * Need to add to get, and you need to get to test the add. 
     */
    @Test
    public void testAddGetRoom() {
        //creating our Room object, filling in all the details
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        
        //call our "addRoom" method to put it into the database.
        room = roomDao.addRoom(room);
        
        //retrieve the Room back out of the database
        Room fromDao = roomDao.getRoomById(room.getId());
        
        //compare
        assertEquals(room, fromDao);
    }
    
    /**
     * Test of getAllRooms method, of class RoomDaoDB.
     */
    @Test
    public void testGetAllRooms() {
        // creating a room with full details and adding it to the database.
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        roomDao.addRoom(room);
        
        Room room2 = new Room();
        room2.setName("Test Room 2");
        room2.setDescription("Test Room 2");
        roomDao.addRoom(room2);
        
        //make our call to "getAllRooms" and save it into a List.
        List<Room> rooms = roomDao.getAllRooms();
        
        //assert that each room is in the list we get back.
        assertEquals(2, rooms.size());
        assertTrue(rooms.contains(room));
        assertTrue(rooms.contains(room2));
    }

    /**
     * Test of updateRoom method, of class RoomDaoDB.
     */
    @Test
    public void testUpdateRoom() {
        // creating a Room with full details and adding it to the database.
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        //pull it back out and verify they are the same.
        Room fromDao = roomDao.getRoomById(room.getId());       
        assertEquals(room, fromDao);
        
        //make a change to the Room and update database
        room.setName("Another Test Room"); 
        roomDao.updateRoom(room);
        
        //assert this new object doesn't match
        assertNotEquals(room, fromDao);
        
        //assert that our local Room object is equal to the one we pulled 
        fromDao = roomDao.getRoomById(room.getId());
        
        assertEquals(room, fromDao);
    }

    /**
     * Test of deleteRoomById method, of class RoomDaoDB.
     */
    @Test
    public void testDeleteRoomById() {
        
        //creating our objects and adding it to the database.
         Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        roomDao.deleteRoomById(room.getId());
        
        //try to retrieve our Room from the database.
        Room fromDao = roomDao.getRoomById(room.getId());
        assertNull(fromDao);
    }
    
}
