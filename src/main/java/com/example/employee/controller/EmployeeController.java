package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.response.EmployeeTaxResponse;
import com.example.employee.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getTax")
    public ResponseEntity<?> getEmployeeTaxDetails(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        
        double totalSalary = employeeService.calculateTotalSalary(employee.getSalary(), employee.getDoj());
        double yearlySalary = totalSalary * 12;
        double taxAmt = employeeService.calculateTax(yearlySalary);
        double cessAmt = employeeService.calculateCessAmt(yearlySalary);
        
        return ResponseEntity.ok(new EmployeeTaxResponse(id, employee.getFirstName(), employee.getLastName(), yearlySalary, taxAmt, cessAmt));
    }

}

