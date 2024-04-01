package com.service;


import java.util.List;

import com.dao.PayrollServiceDao;
import com.interfaces.IPayroll;
import com.model.Payroll;

public class PayrollService implements IPayroll {

    private final PayrollServiceDao payrollDao;

    public PayrollService(PayrollServiceDao payrollServiceDao) {
        this.payrollDao = payrollServiceDao;
    }

	@Override
    public void addPayroll(Payroll payroll) {
        payrollDao.addPayroll(payroll);
    }

    @Override
    public void updatePayroll(Payroll payroll) {
        payrollDao.updatePayroll(payroll);
    }

    @Override
    public Payroll getPayrollById(int payrollId) {
        return payrollDao.getPayrollById(payrollId);
    }

    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollDao.getAllPayrolls();
    }

    @Override
    public void removePayroll(int payrollId) {
        payrollDao.removePayroll(payrollId);
    }
}

