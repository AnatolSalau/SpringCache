package com.example.springcachebyredis.configs;

import com.example.springcachebyredis.entity.Employee;
import com.example.springcachebyredis.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class AppLoadingData {
      final EmployeeRepository employeeRepository;

      @Autowired
      public AppLoadingData(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
      }

      @PostConstruct
      private void loadingData() {
            List<Employee> employees = new LinkedList<>();
            employees.add(new Employee(1, "111111", "FirstName1", "****",
                  "LastName1", "WorkDepth", "+3752599661165", date("2024-01-01"),
                  "Programmer", "High", "Man", date("2001-01-01"), new BigDecimal(111_111), 111.111,
                  111.111));
            employees.add(new Employee(2, "222222", "FirstName2", "****",
                  "LastName2", "WorkDepth", "+3752599661165", date("2024-02-02"),
                  "Programmer", "High", "Man", date("2002-02-02"), new BigDecimal(222_222), 222.222,
                  222.222));
            employees.add(new Employee(3, "333333", "FirstName3", "****",
                  "LastName3", "WorkDepth", "+3752599661165", date("2024-03-03"),
                  "Programmer", "High", "Man", date("2003-03-03"), new BigDecimal(333_333), 333.333,
                  333.333));
            employees.add(new Employee(4, "444444", "FirstName4", "****",
                  "LastName4", "WorkDepth", "+3752599661165", date("2024-05-05"),
                  "Programmer", "High", "Man", date("2004-04-04"), new BigDecimal(444_444), 444.444,
                  444.444));
            employeeRepository.saveAll(employees);
      }

      private Date date(String date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-yy-dd");
            try {
                  return simpleDateFormat.parse(date);
            } catch (ParseException e) {
                  throw new RuntimeException(e);
            }
      }
}
