package com.service;

import java.util.List;

import com.dao.EmployeeServiceDao;
import com.interfaces.IEmployee;
import com.model.Employee;

public class EmployeeService implements IEmployee {
    private EmployeeServiceDao employeeServiceDao;

    public EmployeeService(EmployeeServiceDao employeeServiceDao) {
        this.employeeServiceDao = employeeServiceDao;
    }

	@Override
    public void addEmployee(Employee employee) {
        employeeServiceDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeServiceDao.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeServiceDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeServiceDao.getAllEmployees();
    }

    @Override
    public void removeEmployee(int employeeId) {
        employeeServiceDao.removeEmployee(employeeId);
    }
}
