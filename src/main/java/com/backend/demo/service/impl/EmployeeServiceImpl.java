package com.backend.demo.service.impl;

import com.backend.demo.exception.ResourceNotFoundException;
import com.backend.demo.model.Employee;
import com.backend.demo.repository.EmployeeRepository;
import com.backend.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // First we need to check if the employee is existing in the database or not.
       Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Employee", "Id",id));


       existingEmployee.setFirstName(employee.getFirstName());
       existingEmployee.setLastName(employee.getLastName());
       existingEmployee.setEmail(employee.getEmail());

       //Save existing employee to the database
        employeeRepository.save(existingEmployee);
        return  existingEmployee;


    }

    @Override
    public void deleteEmployee(long id) {

        // check if an Employee does not exist then throw an Exception.
        employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","id",id));
        employeeRepository.deleteById(id);
    }


}
