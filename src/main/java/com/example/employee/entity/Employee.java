package com.example.employee.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "First name should not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phone_number")
    private List<String> phoneNumbers;

    @NotNull(message = "Date of joining is mandatory")
    @Column(name = "doj")
    private LocalDate doj;

    @NotNull(message = "Salary is mandatory")
    @Min(value = 1, message = "Salary must be greater than 0")
    @Column(name = "salary")
    private Double salary;

}