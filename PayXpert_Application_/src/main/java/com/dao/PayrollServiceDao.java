package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Payroll;

public class PayrollServiceDao {
    private Connection connection;

    public PayrollServiceDao(Connection connection) {
        this.connection = connection;
    }

	public PayrollServiceDao() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Method to add a payroll record to the database
    public void addPayroll(Payroll payroll) {
        String query = "INSERT INTO payroll (PayrollID, EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payroll.getPayrollID());
            statement.setInt(2, payroll.getEmployeeID());
            statement.setDate(3, new java.sql.Date(payroll.getPayPeriodStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(payroll.getPayPeriodEndDate().getTime()));
            statement.setDouble(5, payroll.getBasicSalary());
            statement.setDouble(6, payroll.getOvertimePay());
            statement.setDouble(7, payroll.getDeductions());
            statement.setDouble(8, payroll.getNetSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle insert error
        }
    }

    // Method to update a payroll record in the database
    public void updatePayroll(Payroll payroll) {
        String query = "UPDATE payroll SET EmployeeID=?, PayPeriodStartDate=?, PayPeriodEndDate=?, BasicSalary=?, OvertimePay=?, Deductions=?, NetSalary=? WHERE PayrollID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payroll.getEmployeeID());
            statement.setDate(2, new java.sql.Date(payroll.getPayPeriodStartDate().getTime()));
            statement.setDate(3, new java.sql.Date(payroll.getPayPeriodEndDate().getTime()));
            statement.setDouble(4, payroll.getBasicSalary());
            statement.setDouble(5, payroll.getOvertimePay());
            statement.setDouble(6, payroll.getDeductions());
            statement.setDouble(7, payroll.getNetSalary());
            statement.setInt(8, payroll.getPayrollID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle update error
        }
    }

    // Method to retrieve a payroll record by ID from the database
    public Payroll getPayrollById(int payrollId) {
        String query = "SELECT * FROM payroll WHERE PayrollID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payrollId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPayrollFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return null;
    }

    // Method to retrieve all payroll records from the database
    public List<Payroll> getAllPayrolls() {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM payroll";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                payrolls.add(extractPayrollFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle query execution error
        }
        return payrolls;
    }

    // Helper method to extract payroll information from a ResultSet
    private Payroll extractPayrollFromResultSet(ResultSet resultSet) throws SQLException {
        int payrollID = resultSet.getInt("PayrollID");
        int employeeID = resultSet.getInt("EmployeeID");
        Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
        Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
        double basicSalary = resultSet.getDouble("BasicSalary");
        double overtimePay = resultSet.getDouble("OvertimePay");
        double deductions = resultSet.getDouble("Deductions");
        double netSalary = resultSet.getDouble("NetSalary");
        
        return new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate, basicSalary, overtimePay, deductions, netSalary);
    }
    public void removePayroll(int payrollId) {
        String query = "DELETE FROM payroll WHERE PayrollID=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payrollId);
            statement.executeUpdate();
            System.out.println("Payroll with ID " + payrollId + " removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle deletion error
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

