package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional      //to maintain ACID properties
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    // write custom queries (like login check etc)

    @Query(value = "select e from Employee e where e.empName = :empName and e.password= :password")
    public Employee validateEmp(String empName, String password);

}
