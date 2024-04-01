package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Year;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.TaxServiceDao;

public class VerifyTaxCalculationForHighIncomeEmployeeTest {
    private TaxServiceDao taxService;
    private Connection connection;

    @Before
    public void setUp() {
        // Establish database connection
        String url = "jdbc:mysql://localhost:3306/payxpert";
        String username = "root";
        String password = "Rishitha@14";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to establish database connection");
        }
        taxService = new TaxServiceDao(connection);
    }

    @Test
    public void testTaxCalculationForHighIncomeEmployee() {
        // Define parameters for a high-income employee
        int employeeId = 4; // Assuming employee ID 4 is a high-income employee
        Year taxYear = Year.of(2024); // Tax year 2024

        try {
            double calculatedTax = taxService.CalculateTax(employeeId, taxYear);
            double expectedTax = 9060.0; // Expected tax amount for high-income employee with taxable income 45300.00
            assertEquals(expectedTax, calculatedTax, 0.0);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Close database connection
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
