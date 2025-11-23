package com.react.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    @Column(name = "employee_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "employee_name", length = 255)
    private String employeeName;

    @Column(name = "email", length = 255)
    private String emailId;

    @Column(name = "password", length = 255)
    private String password;


}
