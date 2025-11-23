package com.react.controller;


import com.react.entity.Employee;
import com.react.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@Slf4j
@Tag(name = "Employee Controller", description = "REST APIs -Create,Update,Delete.Get All Employee ,Get Employee By ID")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create Employee", description = "Save Employee api is used to create/update a emploee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 Success"),
            @ApiResponse(responseCode = "404", description = "Bad Request")
    })
    @PostMapping(path = "/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employeeDTO) {
        log.info("entered save employee controller");
        Employee emp = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @Operation(summary = "Get Employee", description = "Get Employee api is used to create/update a emploee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 Success"),
            @ApiResponse(responseCode = "404", description = "Bad Request")
    })
    @PostMapping("/login")
    public ResponseEntity<List<Employee>> loginEmployee(@RequestBody Employee loginDTO) {
        List<Employee> loginResponse = employeeService.getEmployee(loginDTO);
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }
}
