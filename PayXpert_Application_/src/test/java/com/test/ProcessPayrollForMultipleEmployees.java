package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.PayrollServiceDao;
import com.model.Payroll;

public class ProcessPayrollForMultipleEmployees {
    PayrollServiceDao payroll;

    @Before
    public void setUp() {
        System.out.println("From Setup() PayrollServiceDao");
        payroll = new PayrollServiceDao();
    }

    @Test
    public void processPayrollForMultipleEmployees() throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Employee 1
            int payrollId1 = 6; // Update with the next available payroll ID
            int employeeId1 = 1;
            Date startDate1 = sdf.parse("2024-04-01");
            Date endDate1 = sdf.parse("2024-04-30");
            double basicSalary1 = 50000.0;
            double overtimePay1 = 2000.0;
            double deductions1 = 5000.0;
            double netSalary1 = basicSalary1 + overtimePay1 - deductions1;

            Payroll payroll1 = new Payroll(payrollId1, employeeId1, startDate1, endDate1, basicSalary1, overtimePay1, deductions1, netSalary1);
            assertNotNull(payroll1);
            payroll.addPayroll(payroll1);

            // Employee 2
            int payrollId2 = 7; // Update with the next available payroll ID
            int employeeId2 = 2;
            Date startDate2 = sdf.parse("2024-04-01");
            Date endDate2 = sdf.parse("2024-04-30");
            double basicSalary2 = 60000.0;
            double overtimePay2 = 2200.0;
            double deductions2 = 6000.0;
            double netSalary2 = basicSalary2 + overtimePay2 - deductions2;

            Payroll payroll2 = new Payroll(payrollId2, employeeId2, startDate2, endDate2, basicSalary2, overtimePay2, deductions2, netSalary2);
            assertNotNull(payroll2);
            payroll.addPayroll(payroll2);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        System.out.println("From tearDown() PayrollServiceDao");
        payroll = null;
    }
}
