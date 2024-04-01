package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.FinancialRecord;

public class FinancialRecordServiceDao {
    private Connection connection;

    public FinancialRecordServiceDao(Connection connection) {
        this.connection = connection;
    }

    // Method to add a financial record to the database
    public void addFinancialRecord(FinancialRecord record) {
        String query = "INSERT INTO financialrecord (RecordID, EmployeeID, RecordDate, Description, RecordType, Amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getRecordID());
            statement.setInt(2, record.getEmployeeID());
            statement.setDate(3, new java.sql.Date(record.getRecordDate().getTime()));
            statement.setString(4, record.getDescription());
            statement.setString(5, record.getRecordType());
            statement.setDouble(6, record.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle insert error
        }
    }

    // Method to update a financial record in the database
    public void updateFinancialRecord(FinancialRecord record) {
        String query = "UPDATE financialrecord SET EmployeeID=?, RecordDate=?, Description=?, RecordType=?, Amount=? WHERE RecordID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getEmployeeID());
            statement.setDate(2, new java.sql.Date(record.getRecordDate().getTime()));
            statement.setString(3, record.getDescription());
            statement.setString(4, record.getRecordType());
            statement.setDouble(5, record.getAmount());
            statement.setInt(6, record.getRecordID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle update error
        }
    }

    // Method to retrieve a financial record by ID from the database
    public FinancialRecord getFinancialRecordById(int recordId) {
        String query = "SELECT * FROM financialrecord WHERE RecordID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFinancialRecordFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return null;
    }

    // Method to retrieve all financial records from the database for a given employee
    public List<FinancialRecord> getFinancialRecordsByEmployeeId(int employeeId) {
        List<FinancialRecord> records = new ArrayList<>();
        String query = "SELECT * FROM financialrecord WHERE EmployeeID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    records.add(extractFinancialRecordFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return records;
    }

    // Helper method to extract financial record information from a ResultSet
    private FinancialRecord extractFinancialRecordFromResultSet(ResultSet resultSet) throws SQLException {
        int recordID = resultSet.getInt("RecordID");
        int employeeID = resultSet.getInt("EmployeeID");
        Date recordDate = resultSet.getDate("RecordDate");
        String description = resultSet.getString("Description");
        String recordType = resultSet.getString("RecordType");
        double amount = resultSet.getDouble("Amount");
        
        return new FinancialRecord(recordID, employeeID, recordDate, description, recordType, amount);
    }
    public List<FinancialRecord> getAllFinancialRecords() {
        List<FinancialRecord> records = new ArrayList<>();
        String query = "SELECT * FROM financialrecord";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                records.add(extractFinancialRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return records;
    }

    // Method to remove a financial record from the database by ID
    public void removeFinancialRecord(int recordId) {
        String query = "DELETE FROM financial_records WHERE RecordID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle delete error
        }
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

