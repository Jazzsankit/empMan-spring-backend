package com.example.empManspringbackend.controller;

import com.example.empManspringbackend.exception.ResourceNotFound;
import com.example.empManspringbackend.model.Employee;
import com.example.empManspringbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createNewEmployee(@RequestBody @Valid Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping ("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Employee not exist with id: "+id));
        return ResponseEntity.ok(employee);
    }


    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,@RequestBody Employee employee){
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Employee not exist with id: "+id));

        updateEmployee.setName(employee.getName());
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setPhNumber(employee.getPhNumber());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Employee not exist with id: "+id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
