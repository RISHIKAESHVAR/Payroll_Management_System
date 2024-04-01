package com.interfaces;

import java.util.List;

import com.model.Employee;

public interface IEmployee {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void removeEmployee(int employeeId);
}
