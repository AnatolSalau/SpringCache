package com.example.springcachebyredis.repository;

import com.example.springcachebyredis.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

      Optional<Employee> findEmployeeByEmployeeNo(String employeeNo);

      @Override
      <S extends Employee> List<S> findAll(Example<S> example);

      @Override
      <S extends Employee> S save(S entity);

}
