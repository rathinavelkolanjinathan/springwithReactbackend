package com.react.service;

import com.react.dto.EmployeeDTO;
import com.react.dto.LoginDTO;
import com.react.responseload.LoginResponse;


public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
}
