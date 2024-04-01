package com.interfaces;

import java.util.List;

import com.model.Payroll;

public interface IPayroll {
    void addPayroll(Payroll payroll);
    void updatePayroll(Payroll payroll);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getAllPayrolls();
    void removePayroll(int payrollId);
}
