package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Exceptions.EmployeeIDAlreadyExists;
import com.example.EmployeeManagement.Model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee addEmployee(Employee employee) throws EmployeeIDAlreadyExists;

    public boolean validateEmployee(String empName, String password);

    public Employee findEmpByID(int empID);
}

