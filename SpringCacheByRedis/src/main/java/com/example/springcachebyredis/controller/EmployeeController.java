package com.example.springcachebyredis.controller;

import com.example.springcachebyredis.dto.EmployeeDto;
import com.example.springcachebyredis.exceptions.EmployeeNotFoundException;
import com.example.springcachebyredis.services.EmployeeService;
import com.example.springcachebyredis.services.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class EmployeeController {
      private final EmployeeServiceImpl employeeService;

      @Autowired
      public EmployeeController(EmployeeServiceImpl employeeService) {
            this.employeeService = employeeService;
      }

      @GetMapping("emp")
      public ResponseEntity<Object> findEmployeeByNo(@RequestParam String empNo) {
            log.info("GET-- employee by employee_no {}", empNo);
            try {
                  EmployeeDto employee = employeeService.findByEmployeeNo(empNo);
                  return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(employee);
            } catch (Exception ex ){
                  return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new EmployeeNotFoundException("Unexpected error"));
            }
      }

      @GetMapping("emp/all")
      public ResponseEntity<Object> getAllEmployee () {
            log.info("GET-- all employees");
            try {
                  List<EmployeeDto> all = employeeService.getAll();
                  return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(all);
            } catch (Exception ex ){
                  return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new EmployeeNotFoundException("Unexpected error"));
            }
      }

      @PostMapping("emp")
      public ResponseEntity<Object> updateEmployee(EmployeeDto employeeDto) {
            log.info("UPDATE-- employee {}", employeeDto);
            try {
                  EmployeeDto employee = employeeService.update(employeeDto, employeeDto.getEmployeeNo());
                  return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(employee);
            } catch (Exception ex ){
                  return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new EmployeeNotFoundException("Unexpected error"));
            }
      }

      @DeleteMapping("emp/{empNo}")
      public ResponseEntity<Object> deleteEmployee(@PathVariable("empNo") String empNo) {
            log.info("UPDATE-- employee no {}", empNo);
            try {
                  employeeService.deleteByEmployeeNo(empNo);
                  return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("");
            } catch (Exception ex ){
                  return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new EmployeeNotFoundException("Unexpected error"));
            }
      }

}
