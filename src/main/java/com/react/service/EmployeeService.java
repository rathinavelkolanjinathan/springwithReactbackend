package com.react.service;

import com.react.entity.Employee;

import java.util.List;


public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getEmployee(Employee loginDTO);
}
