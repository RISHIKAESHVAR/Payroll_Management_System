package com.test;

import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.EmployeeServiceDao;
import com.model.Employee;
import com.util.SqlConnection; // Import the SqlConnection class

public class VerifyErrorHandlingForInvalidEmployeeData {
    private Connection connection;
    private EmployeeServiceDao employeeServiceDao;

    @Before
    public void setUp() throws SQLException {
        connection = SqlConnection.getConnection(); // Establish connection
        employeeServiceDao = new EmployeeServiceDao(connection); // Pass the connection to the constructor
    }

    @Test
    public void testInvalidEmployeeData() throws ParseException {
        Employee invalidEmployee = employeeServiceDao.getEmployeeById(190);
        assertNull(invalidEmployee);
        System.out.println("No employee is Found");
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null) {
            SqlConnection.closeConnection(connection); // Close connection
        }
    }
}
