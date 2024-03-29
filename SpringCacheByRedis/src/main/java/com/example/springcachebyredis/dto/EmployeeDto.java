package com.example.springcachebyredis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeDto {

      @JsonProperty("id")
      private Integer id;

      @JsonProperty("employee_no")
      private String employeeNo;

      @JsonProperty("first_name")
      private String firstName;

      @JsonProperty("mid_init")
      private String midInit;

      @JsonProperty("last_name")
      private String lastName;

      @JsonProperty("work_dept")
      private String workDept;

      @JsonProperty("phone_no")
      private String phoneNo;

      @JsonProperty("hire_date")
      private Date hireDate;

      @JsonProperty("job")
      private String job;

      @JsonProperty("ed_level")
      private String edLevel;

      @JsonProperty("sex")
      private String sex;

      @JsonProperty("birthDate")
      private Date birthDate;

      @JsonProperty("salary")
      private BigDecimal salary;

      @JsonProperty("bonus")
      private Double bonus;

      @JsonProperty("comission")
      private Double comission;
}
