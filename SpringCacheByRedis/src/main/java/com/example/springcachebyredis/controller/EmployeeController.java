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
      public ResponseEntity<Object> findEmployeeByNo(@RequestParam String empNo) throws InterruptedException {
            log.info("GET-- employee by employee_no {} from DB", empNo);
            Thread.sleep(2000);
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
      public ResponseEntity<Object> getAllEmployee () throws InterruptedException {
            log.info("GET-- all employees from DB");
            Thread.sleep(2000);
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
      public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto employeeDto) throws InterruptedException {
            log.info("UPDATE-- employee from DB {}", employeeDto);
            Thread.sleep(2000);
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
      public ResponseEntity<Object> deleteEmployee(@PathVariable("empNo") String empNo) throws InterruptedException {
            log.info("DELETE-- employee no {} from DB", empNo);
            Thread.sleep(2000);
            try {
                  employeeService.deleteByEmployeeNo(empNo);
                  return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Was deleted");
            } catch (Exception ex ){
                  return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new EmployeeNotFoundException("Unexpected error"));
            }
      }

}
