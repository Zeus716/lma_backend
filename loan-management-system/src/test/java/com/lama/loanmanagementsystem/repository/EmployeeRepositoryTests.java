package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.EmployeeMaster;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeMasterRepository empRep;
    @Autowired
    private TestEntityManager testEntityManager;
//    @Autowired
    private EmployeeMaster employeeMaster;
    private EmployeeMaster employeeMasterNotSaved;
    @BeforeEach
    public void setup(){
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.minusYears(20);
        Date today = java.sql.Date.valueOf(date);
        Date dob = java.sql.Date.valueOf(date1);
        employeeMaster = EmployeeMaster.builder().employeeName("ramesh").employeeDepartment("testdept")
                .employeeDOB(today).employeeDOJ(today).employeeDesignation("testdesignation").gender("M").build();
        employeeMasterNotSaved = EmployeeMaster.builder().employeeName("ramesh").employeeDepartment("testdept")
                .employeeDOB(today).employeeDOJ(today).employeeDesignation("testdesignation").gender("M").build();
    }
    @DisplayName("save employee")
    @Test
    public void checkEmployeeSaved(){
        Integer a = empRep.findAll().size();
        empRep.save(employeeMaster);
        empRep.save(employeeMasterNotSaved);
        List<EmployeeMaster> employees = empRep.findAll();
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(a+2 );
    }

    @DisplayName("get employee by id")
    @Test
    public void checkEmployeefetch(){
        empRep.save(employeeMaster);
        EmployeeMaster employeeMaster1 = empRep.findById(employeeMaster.getEmployeeId()).get();
        Assertions.assertThat(employeeMaster1).isNotNull();
    }

    @DisplayName("test update")

    @Test
    public void updateEmployeecheck(){
        empRep.save(employeeMaster);
        EmployeeMaster employeeMaster1 = empRep.findById(employeeMaster.getEmployeeId()).get();
        employeeMaster1.setEmployeeDepartment("updatedDepartment");
        employeeMaster1.setEmployeeDesignation("updatedDesignation");
        employeeMaster1.setEmployeeName("updatedName");
        employeeMaster1.setGender("updatedGender");
//        employeeMaster1.setEmployeeDOJ(new Date("2001-12-10"));
//        employeeMaster1.setEmployeeDOB(new Date("1980-12-10"));
        EmployeeMaster employeeMaster2 = empRep.save(employeeMaster1);
        Assertions.assertThat(employeeMaster2.getEmployeeDepartment()).isEqualTo("updatedDepartment");
//        Assertions.assertThat(employeeMaster2.getEmployeeDOB().toString()).isEqualTo("2001-12-10");
//        Assertions.assertThat(employeeMaster2.getEmployeeDOJ().toString()).isEqualTo("1980-12-10");
        Assertions.assertThat(employeeMaster2.getEmployeeName()).isEqualTo("updatedName");
        Assertions.assertThat(employeeMaster2.getEmployeeDesignation()).isEqualTo("updatedDesignation");
        Assertions.assertThat(employeeMaster2.getGender()).isEqualTo("updatedGender");

    }

    @DisplayName("delete test")
    @Test
    public void deleteEmployeeCheck(){
        empRep.save(employeeMaster);
        empRep.deleteById(employeeMaster.getEmployeeId());
        Optional<EmployeeMaster> employeeexists = empRep.findById(employeeMaster.getEmployeeId());
        Assertions.assertThat(employeeexists).isEmpty();
    }
}
