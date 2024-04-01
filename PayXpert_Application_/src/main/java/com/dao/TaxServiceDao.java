package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Tax;

import java.time.Year;

public class TaxServiceDao {
    private Connection connection;

    public TaxServiceDao(Connection connection) {
        this.connection = connection;
    }

    public TaxServiceDao() {
		// TODO Auto-generated constructor stub
    	super();
	}

	// Method to add a tax record to the database
    public void addTax(Tax tax) {
        String query = "INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tax.getEmployeeID());
            statement.setInt(2, tax.getTaxYear().getValue());
            statement.setDouble(3, tax.getTaxableIncome());
            statement.setDouble(4, tax.getTaxAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle insert error
        }
    }

    // Method to update a tax record in the database
    public void updateTax(Tax tax) {
        String query = "UPDATE Tax SET EmployeeID=?, TaxYear=?, TaxableIncome=?, TaxAmount=? WHERE TaxID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tax.getEmployeeID());
            statement.setInt(2, tax.getTaxYear().getValue());
            statement.setDouble(3, tax.getTaxableIncome());
            statement.setDouble(4, tax.getTaxAmount());
            statement.setInt(5, tax.getTaxID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle update error
        }
    }

    // Method to retrieve a tax record by ID from the database
    public Tax getTaxById(int taxId) {
        String query = "SELECT * FROM Tax WHERE TaxID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, taxId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractTaxFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return null;
    }

    // Method to retrieve all tax records from the database
    public List<Tax> getAllTaxes() {
        List<Tax> taxes = new ArrayList<>();
        String query = "SELECT * FROM Tax";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                taxes.add(extractTaxFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return taxes;
    }

    // Method to remove a tax record from the database
    public void removeTax(int taxId) {
        String query = "DELETE FROM Tax WHERE TaxID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, taxId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle delete error
        }
    }

    // Helper method to extract tax information from a ResultSet
    private Tax extractTaxFromResultSet(ResultSet resultSet) throws SQLException {
        int taxID = resultSet.getInt("TaxID");
        int employeeID = resultSet.getInt("EmployeeID");
        Year taxYear = Year.of(resultSet.getInt("TaxYear"));
        double taxableIncome = resultSet.getDouble("TaxableIncome");
        double taxAmount = resultSet.getDouble("TaxAmount");
        
        return new Tax(taxID, employeeID, taxYear, taxableIncome, taxAmount);
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

	public double CalculateTax(int employeeId, Year taxYear) {
		// TODO Auto-generated method stub
		return 0;
	}
}
