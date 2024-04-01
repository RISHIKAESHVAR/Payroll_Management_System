package com.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.FinancialRecordServiceDao;
import com.dao.PayrollServiceDao;
import com.model.Payroll;
import com.util.SqlConnection;

public class CalculateNetSalaryAfterDeductionsTest {
    private Connection connection;
    private PayrollServiceDao payrollServiceDao;

    @Before
    public void setUp() throws SQLException {
        connection = SqlConnection.getConnection(); // Establish connection
        payrollServiceDao = new PayrollServiceDao(connection);
        new FinancialRecordServiceDao(connection);
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null) {
            SqlConnection.closeConnection(connection);
        }
    }

    @Test
    public void testCalculateNetSalaryAfterDeductions() {
        try {
            // Prepare test data
            int payrollID = 1; // ID of the payroll for which we want to calculate the net salary
            double basicSalary = 50000.0; // Sample basic salary
            double overtimePay = 2000.0; // Sample overtime pay
            double deductions = 5000.0; // Sample deductions (taxes, insurance, etc.)
            double expectedNetSalary = basicSalary + overtimePay - deductions; // Expected net salary

            // Create a sample payroll record
            Payroll payroll = new Payroll(payrollID, 1, new Date(), new Date(), basicSalary, overtimePay, deductions, 0.0);
            payrollServiceDao.addPayroll(payroll);

            // Execute the functionality to calculate the net salary
            double actualNetSalary = payrollServiceDao.getPayrollById(payrollID).getNetSalary();

            // Assert the result
            assertEquals(expectedNetSalary, actualNetSalary, 0.01); // Allow a small delta for double comparison
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}