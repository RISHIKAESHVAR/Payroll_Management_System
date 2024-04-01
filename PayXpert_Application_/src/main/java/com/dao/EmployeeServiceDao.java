package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Employee;

public class EmployeeServiceDao {
    private Connection connection;

    public EmployeeServiceDao(Connection connection) {
        this.connection = connection;
    }

    public EmployeeServiceDao() {
		// TODO Auto-generated constructor stub
	}

	// Method to add an employee to the database
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employee (EmployeeID, First_Name, Last_Name, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getEmployeeID());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setDate(4, new java.sql.Date(employee.getDateOfBirth().getTime()));
            statement.setString(5, employee.getGender());
            statement.setString(6, employee.getEmail());
            statement.setString(7, employee.getPhoneNumber());
            statement.setString(8, employee.getAddress());
            statement.setString(9, employee.getPosition());
            statement.setDate(10, new java.sql.Date(employee.getJoiningDate().getTime()));
            statement.setDate(11, employee.getTerminationDate() != null ? new java.sql.Date(employee.getTerminationDate().getTime()) : null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle insert error
        }
    }

    // Method to update an employee in the database
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employee SET First_Name=?, Last_Name=?, DateOfBirth=?, Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?, JoiningDate=?, TerminationDate=? WHERE EmployeeID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));
            statement.setString(4, employee.getGender());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getPhoneNumber());
            statement.setString(7, employee.getAddress());
            statement.setString(8, employee.getPosition());
            statement.setDate(9, new java.sql.Date(employee.getJoiningDate().getTime()));
            statement.setDate(10, employee.getTerminationDate() != null ? new java.sql.Date(employee.getTerminationDate().getTime()) : null);
            statement.setInt(11, employee.getEmployeeID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle update error
        }
    }

    // Method to retrieve an employee by ID from the database
    public Employee getEmployeeById(int employeeId) {
        String query = "SELECT * FROM employee WHERE EmployeeID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractEmployeeFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return null;
    }

    // Method to retrieve all employees from the database
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return employees;
    }

    // Method to remove an employee from the database
    public void removeEmployee(int employeeId) {
        String query = "DELETE FROM employee WHERE EmployeeID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle delete error
        }
    }

    // Helper method to extract employee information from a ResultSet
    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int employeeID = resultSet.getInt("EmployeeID");
        String firstName = resultSet.getString("First_Name");
        String lastName = resultSet.getString("Last_Name");
        Date dateOfBirth = resultSet.getDate("DateOfBirth");
        String gender = resultSet.getString("Gender");
        String email = resultSet.getString("Email");
        String phoneNumber = resultSet.getString("PhoneNumber");
        String address = resultSet.getString("Address");
        String position = resultSet.getString("Position");
        Date joiningDate = resultSet.getDate("JoiningDate");
        Date terminationDate = resultSet.getDate("TerminationDate");
        
        return new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);
    }

    // Close connection method (optional)
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

