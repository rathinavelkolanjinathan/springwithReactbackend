package com.react.service.impl;

import com.react.entity.Employee;
import com.react.exception.ResourceNotFoundException;
import com.react.repository.EmployeeRepository;
import com.react.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.react.constant.ExceptionConstant.*;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    //@Autowired
    // private PasswordEncoder passwordEncoder;


    @Override
    public Employee createEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employee.getEmployeeId());
        if (optionalEmployee.isPresent()) {
            Employee emp = employeeRepo.save(employee);
            log.info("Employee added successfully: {}", employee.getEmployeeName());
            if (emp != null)
                return emp;
            else
                throw new ResourceNotFoundException(EMPLOYEE_CREATION_FAILURE);
        } else {
            throw new ResourceNotFoundException(EMPLOYEE_ALREADY_EXISTS);
        }

    }


    @Override
    public List<Employee> getEmployee(Employee employee) {

        List<Employee> empList = employeeRepo.findByUserName(employee.getEmailId());
        if (!empList.isEmpty()) {
            return empList;
        } else {

            //log.error("Employee not found with email: {}", employee.getEmailId());
            throw new ResourceNotFoundException(EMPLOYEE_NOT_FOUND);
        }
    }

}
