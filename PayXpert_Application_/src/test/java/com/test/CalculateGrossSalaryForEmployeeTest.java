package com.test;

import static org.junit.Assert.assertEquals;


import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.FinancialRecordServiceDao;
import com.dao.PayrollServiceDao;
import com.model.Payroll;
import com.util.SqlConnection;

public class CalculateGrossSalaryForEmployeeTest {
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
    public void testCalculateGrossSalaryForEmployee() {
        try {
            // Prepare test data
            int employeeID = 1; // ID of the employee for whom we want to calculate the gross salary

            // Retrieve basic salary, overtime pay, and deductions from the database
            Payroll payrollData = payrollServiceDao.getPayrollById(employeeID);
            double basicSalary = payrollData.getBasicSalary();
            double overtimePay = payrollData.getOvertimePay();
            double deductions = payrollData.getDeductions();

            // Calculate expected gross salary
            double expectedGrossSalary = basicSalary + overtimePay - deductions;

            // Actual Salary
            double actualGrossSalary = payrollServiceDao.getPayrollById(employeeID).getNetSalary();

            // Assert the result
            assertEquals(expectedGrossSalary, actualGrossSalary, 0.01); // Allow a small delta for double comparison
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

