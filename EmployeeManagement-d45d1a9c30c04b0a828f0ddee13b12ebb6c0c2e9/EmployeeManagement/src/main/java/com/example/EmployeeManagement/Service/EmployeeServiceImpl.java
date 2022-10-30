package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Exceptions.EmployeeIDAlreadyExists;
import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // create Object of Repo Layer to get data and perform CRUD OPs
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        if (employeeList.size()>0){
            return employeeList;
        } else{
            return null;
        }
    }

       @Override
        public Employee findEmpByID(int empID) {
        Optional<Employee> employee = employeeRepo.findById(empID);
        if (employee.isPresent()){
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee addEmployee(Employee employee) throws EmployeeIDAlreadyExists {
        Optional <Employee> companyOptional = employeeRepo.findById(employee.getEmpID());
        if (companyOptional.isPresent()){
            throw new EmployeeIDAlreadyExists();
        }
        employeeRepo.saveAndFlush(employee);
        return employee;
    }

//    @Override
//    public boolean deleteEmployee(int empID) {
//        employeeRepo.deleteById(empID);
//        return true;
//    }

//    @Override
//    public boolean updateEmployee(Employee employee) {
//        Employee employee1 = employeeRepo.getById(employee.getEmpID());
//
//        employee1.setEmpName(employee.getEmpName());
//        employeeRepo.saveAndFlush(employee1);
//        return true;
//    }

    @Override
    public boolean validateEmployee(String empName, String password) {
        Employee employee = employeeRepo.validateEmp(empName, password);
        return employee != null;
    }


}
