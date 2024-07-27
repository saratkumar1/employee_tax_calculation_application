package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmployeeServiceImpl implements EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("InValid EmployeeID ,Employee not found"));
    }
    @Override
    public double calculateTax(double yearlySalary) {
        double taxAmt = 0;
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary <= 500000) {
        	taxAmt += (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
        	taxAmt += 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
        } else {
        	taxAmt += 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
        }
        return taxAmt;
    }
    @Override
    public double calculateCessAmt(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0;
    }
    @Override
    public double calculateTotalSalary(double monthlySalary, LocalDate doj) {
        LocalDate now = LocalDate.now();
        int monthsWorked = (int) ChronoUnit.MONTHS.between(doj.withDayOfMonth(1), now.withDayOfMonth(1));
        double daysInFirstMonth = 30 - doj.getDayOfMonth() + 1;
        double dailySalary = monthlySalary / 30;
        double totalSalary = (monthsWorked - 1) * monthlySalary + dailySalary * daysInFirstMonth;
        return totalSalary;
    }
}
