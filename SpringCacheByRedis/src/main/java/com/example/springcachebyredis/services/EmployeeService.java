package com.example.springcachebyredis.services;

import com.example.springcachebyredis.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

      EmployeeDto findByEmployeeNo(String employeeNo) throws InterruptedException;

      List<EmployeeDto> getAll() throws InterruptedException;

      EmployeeDto update(EmployeeDto employeeDto, String employeeNo) throws InterruptedException;

      boolean deleteByEmployeeNo(String employeeNo) throws InterruptedException;

}
