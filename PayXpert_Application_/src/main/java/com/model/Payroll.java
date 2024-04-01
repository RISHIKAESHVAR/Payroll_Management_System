package com.model;

import java.util.Date;

public class Payroll {
	int PayrollID, EmployeeID;
	Date PayPeriodStartDate, PayPeriodEndDate;
	Double BasicSalary, OvertimePay, Deductions, NetSalary;
	public Payroll() {
		super();
	}
	public Payroll(int payrollID, int employeeID, Date payPeriodStartDate, Date payPeriodEndDate, Double basicSalary,
			Double overtimePay, Double deductions, Double netSalary) {
		super();
		PayrollID = payrollID;
		EmployeeID = employeeID;
		PayPeriodStartDate = payPeriodStartDate;
		PayPeriodEndDate = payPeriodEndDate;
		BasicSalary = basicSalary;
		OvertimePay = overtimePay;
		Deductions = deductions;
		NetSalary = netSalary;
	}
	public Payroll(int employeeID, Date payPeriodStartDate, Date payPeriodEndDate, double basicSalary,
			double overtimePay, double deductions) {
		EmployeeID = employeeID;
		PayPeriodStartDate = payPeriodStartDate;
		PayPeriodEndDate = payPeriodEndDate;
		BasicSalary = basicSalary;
		OvertimePay = overtimePay;
		Deductions = deductions;
	}
	public int getPayrollID() {
		return PayrollID;
	}
	public void setPayrollID(int payrollID) {
		PayrollID = payrollID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public Date getPayPeriodStartDate() {
		return PayPeriodStartDate;
	}
	public void setPayPeriodStartDate(Date payPeriodStartDate) {
		PayPeriodStartDate = payPeriodStartDate;
	}
	public Date getPayPeriodEndDate() {
		return PayPeriodEndDate;
	}
	public void setPayPeriodEndDate(Date payPeriodEndDate) {
		PayPeriodEndDate = payPeriodEndDate;
	}
	public Double getBasicSalary() {
		return BasicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		BasicSalary = basicSalary;
	}
	public Double getOvertimePay() {
		return OvertimePay;
	}
	public void setOvertimePay(Double overtimePay) {
		OvertimePay = overtimePay;
	}
	public Double getDeductions() {
		return Deductions;
	}
	public void setDeductions(Double deductions) {
		Deductions = deductions;
	}
	public Double getNetSalary() {
		return NetSalary;
	}
	public void setNetSalary(Double netSalary) {
		NetSalary = netSalary;
	}
	@Override
	public String toString() {
		return "Payroll [PayrollID=" + PayrollID + ", EmployeeID=" + EmployeeID + ", PayPeriodStartDate="
				+ PayPeriodStartDate + ", PayPeriodEndDate=" + PayPeriodEndDate + ", BasicSalary=" + BasicSalary
				+ ", OvertimePay=" + OvertimePay + ", Deductions=" + Deductions + ", NetSalary=" + NetSalary + "]";
	}
	

}