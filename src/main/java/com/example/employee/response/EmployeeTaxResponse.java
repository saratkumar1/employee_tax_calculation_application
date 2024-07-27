package com.example.employee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaxResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double tax;
    private double cess;
}
