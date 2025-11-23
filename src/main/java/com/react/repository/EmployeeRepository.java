package com.react.repository;


import com.react.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     @Query("SELECT e FROM Employee e WHERE e.emailId = :emailId AND e.password = :password")
     Optional<Employee> findByEmailAndPassword(@Param("emailId")
                                                  String emailId, @Param("password") String password);

     @Query("SELECT e FROM Employee e WHERE e.emailId = :emailId")
     List<Employee> findByUserName(@Param("emailId") String emailId);
}
