package com.example.springcachebyredis.services;

import com.example.springcachebyredis.dto.EmployeeDto;
import com.example.springcachebyredis.entity.Employee;
import com.example.springcachebyredis.exceptions.EmployeeNotFoundException;
import com.example.springcachebyredis.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
      @Cacheable(value = "EmployeeDto", key = "#employeeNo")
      public EmployeeDto findByEmployeeNo(String employeeNo) throws InterruptedException {

            Thread.sleep(5000);

            Optional<Employee> employee = employeeRepository.findEmployeeByEmployeeNo(employeeNo);
            log.info("GET-- employee by employee_no from DB {}", employee);
            EmployeeDto employeeDto = employee
                  .map(em -> modelMapper.map(em, EmployeeDto.class))
                  .orElseThrow(() ->new EmployeeNotFoundException("Employee not found"));

            return employeeDto;
      }

      @Override
      @Cacheable(value = "EmployeeDtoList")
      public List<EmployeeDto> getAll() throws InterruptedException {
            Thread.sleep(5000);
            List<Employee> employees = employeeRepository.findAll();
            log.info("GET-- all employee from DB {}", employees);
            if (!employees.isEmpty()) {
                 return employees.stream()
                       .map(employee -> modelMapper.map(employee,EmployeeDto.class))
                       .collect(Collectors.toList());
            }
            throw new EmployeeNotFoundException("List of employees  not found");
      }

      @Override
      @Caching(
            put = {@CachePut(value = "EmployeeDto", key = "#employeeNo")},
            evict = {@CacheEvict(value = "EmployeeDtoList", allEntries = true)}
      )
      public EmployeeDto update(EmployeeDto employeeDto, String employeeNo) throws InterruptedException {
            Thread.sleep(5000);
            Optional<Employee> employee = employeeRepository.findEmployeeByEmployeeNo(employeeNo);
            log.info("UPDATE-- update employee in DB {}", employee);
            if (employee.isPresent()) {
                  employee.get().setFirstName(employeeDto.getFirstName());
                  employee.get().setLastName(employeeDto.getLastName());
                  employee.get().setSex(employeeDto.getSex());
                  employee.get().setEdLevel(employeeDto.getEdLevel());
                  Employee temp = employeeRepository.save(employee.get());
                  return modelMapper.map(temp,EmployeeDto.class);
            }
            log.error("No employee no {} found", employeeDto);
            return null;
      }

      @Caching(evict = {
            @CacheEvict(value = "EmployeeDto", key = "#employeeNo"),
            @CacheEvict(value = "EmployeeDtoList", allEntries = true)
      })
      @Override
      public boolean deleteByEmployeeNo(String employeeNo) throws InterruptedException {
            Thread.sleep(5000);
            Optional<Employee> employee = employeeRepository.findEmployeeByEmployeeNo(employeeNo);
            log.info("DELETE-- delete employee by  employee_no in DB {}", employeeNo);
            if (employee.isPresent()) {
                  employeeRepository.delete(employee.get());
                  boolean existsById = employeeRepository.existsById(employee.get().getId());
                  return !existsById;
            }
            log.error("No employee no {} found", employee);
            return false;
      }
}
