package com.dmitriynikanorov.spring.rest.controllers;


import com.dmitriynikanorov.spring.rest.entity.Employee;
import com.dmitriynikanorov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.dmitriynikanorov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.dmitriynikanorov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private EmployeeService service;

    @Autowired
    public RESTController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = service.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {

        Employee emp = service.getEmployee(id);
        if (emp == null) {
            throw new NoSuchEmployeeException("There is no Employee with id = " +
                    id + " in DB");
        }

        return emp;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employeeToDelete = service.getEmployee(id);

        if (employeeToDelete == null) {
        throw new  NoSuchEmployeeException("There is no Employee with id = " +
                id + " in DB)");
        }

        service.deleteEmployee(id);
        return "Emplyee with id = " + id + " was deleted";
    }
//    @ExceptionHandler
//    public ResponseEntity<EmployeeIncorrectData> HandleException(
//            NoSuchEmployeeException exception){
//    EmployeeIncorrectData data = new EmployeeIncorrectData();
//    data.setInfo(exception.getMessage());
//    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<EmployeeIncorrectData> HandleException(
//           Exception exception){
//        EmployeeIncorrectData data = new EmployeeIncorrectData();
//        data.setInfo(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }
}
