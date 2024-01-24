package com.example.springcachebyredis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "employee")

public class Employee implements Serializable {

      @Serial
      private static final long serialVersionUID = 7896260843520269196L;

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @Column(name = "employee_no", unique = true)
      private String employeeNo;

      private String firstName;

      private String midInit;

      private String lastName;

      private String workDept;

      private String phoneNo;

      private Date hireDate;

      private String job;

      private String edLevel;

      private String sex;

      private Date birthDate;

      private BigDecimal salary;

      private Double bonus;

      private Double comission;
}
