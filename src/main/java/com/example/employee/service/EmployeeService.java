package com.example.employee.service;

import com.example.employee.entity.Employee;
import java.time.LocalDate;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    double calculateTax(double yearlySalary);
    double calculateCessAmt(double yearlySalary);
    double calculateTotalSalary(double monthlySalary, LocalDate doj);
}