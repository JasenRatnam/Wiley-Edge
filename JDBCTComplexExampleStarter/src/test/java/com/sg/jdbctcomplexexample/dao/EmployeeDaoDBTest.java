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
public class EmployeeDaoDBTest {
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    MeetingDao meetingDao;
    
    public EmployeeDaoDBTest() {
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
     * test  "addEmployee" and "getEmployeeById"
     */
    @Test
    public void testAddGetEmployee() {
        //creating our Employee object with full details and adding it 
        //into the database with "addEmployee".
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        //retrieve the Employee back out with "getEmployeeById".
        Employee fromDao1 = employeeDao.getEmployeeById(employee.getId());
        
        //assert
        assertEquals(employee, fromDao1);
    }
    
    /**
     * test getAllEmployees
     */
    @Test
    public void testGetAllEmployees() {
        //create our two Employee objects with full details 
        //add them both to the database.
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);
        
        //retrieve the List of Employee objects using
        List<Employee> employees = employeeDao.getAllEmployees();
        
        //assert that we've retrieved two Employee objects.
        //assert that each object is in our List.
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee));
        assertTrue(employees.contains(employee2));
    }
    
    /**
     * test updateEmployee
     */
    @Test
    public void testUpdateEmployee() {
        //creating our Employee object with full details and saving it to the database.
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertEquals(employee, fromDao);
        
        
        //make a change to the local Employee
        employee.setFirstName("Another Test First");
        
        employeeDao.updateEmployee(employee);
        
        assertNotEquals(employee, fromDao);
        
        fromDao = employeeDao.getEmployeeById(employee.getId());
        
        //ssert that the local Employee is equal
        assertEquals(employee, fromDao);
    }
    
    /**
     * test deleteEmployee
     */
    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        employeeDao.deleteEmployeeById(employee.getId());
        
        // trying to retrieve the Employee back
        Employee fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertNull(fromDao);
    }
    
    
}
