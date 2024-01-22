package com.example.springcachebyredis.services;

import com.example.springcachebyredis.dto.EmployeeDto;
import com.example.springcachebyredis.entity.Employee;
import com.example.springcachebyredis.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements  EmployeeService{

      private final EmployeeRepository employeeRepository;
      private final ModelMapper modelMapper;

      @Autowired
      public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
            this.employeeRepository = employeeRepository;
            this.modelMapper = modelMapper;
      }

      @Override
      public EmployeeDto findByEmployeeNo(String employeeNo) {
            Optional<Employee> employee = employeeRepository.findEmployeeByEmployeeNo(employeeNo);
            EmployeeDto employeeDto = employee
                  .map(em -> modelMapper.map(em, EmployeeDto.class))
                  .orElse(null);

            return employeeDto;
      }

      @Override
      public List<EmployeeDto> getAll() {
            List<Employee> employees = employeeRepository.findAll();
            if (!employees.isEmpty()) {
                 return employees.stream()
                       .map(employee -> modelMapper.map(employee,EmployeeDto.class))
                       .collect(Collectors.toList());
            }
            return null;
      }

      @Override
      public EmployeeDto update(EmployeeDto employeeDto, String employeeNo) {
            return null;
      }

      @Override
      public boolean deleteByEmployeeNo(String employeeNo) {
            return false;
      }
}
