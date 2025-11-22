package com.react.controller;


import com.react.dto.EmployeeDTO;
import com.react.dto.LoginDTO;
import com.react.responseload.LoginResponse;
import com.react.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path="/save", consumes = "application/json")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        log.info("entered save employee controller");
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }
}
