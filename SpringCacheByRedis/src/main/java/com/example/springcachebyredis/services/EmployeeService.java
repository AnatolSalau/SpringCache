package com.example.springcachebyredis.services;

import com.example.springcachebyredis.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

      EmployeeDto findByEmployeeNo(String employeeNo) throws InterruptedException;

      List<EmployeeDto> getAll();

      EmployeeDto update(EmployeeDto employeeDto, String employeeNo);

      boolean deleteByEmployeeNo(String employeeNo);

}
