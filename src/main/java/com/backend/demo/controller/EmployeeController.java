package com.backend.demo.controller;

import com.backend.demo.model.Employee;
import com.backend.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
//Created a REST API for post request for request from (postman) to MySQL database
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Build create employee REST API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    //build (get) all employee Rest API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    //////
    //Build get employee by id REST API
    //http://127.0.0.1:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }
    //////
    //Build update employee REST API
    //http://127.0.0.1:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);
    }

    //Build delete Employee REST API
    //http://127.0.0.1:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")long id){
        //delete employee from Database
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully.", HttpStatus.OK);
    }

}
