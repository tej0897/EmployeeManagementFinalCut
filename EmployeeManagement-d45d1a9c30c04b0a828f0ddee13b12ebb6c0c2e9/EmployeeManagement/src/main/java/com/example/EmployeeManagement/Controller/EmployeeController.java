package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/getAllEmp")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        if (employeeList != null) {
            return new ResponseEntity<List>(employeeList, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Employee List is empty", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findEmp/{empID}")
    public ResponseEntity<?> findEmp(@PathVariable("empID") int empID){
        Employee employee = employeeService.findEmpByID(empID);
        if (employee!=null){
            return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
        }
        return new ResponseEntity<String>("Employee Not Found",HttpStatus.NOT_FOUND);
    }
}



//    @PostMapping("/addEmp")
//    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
//        if (employeeService.addEmployee(employee)!=null){
//            return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<String>("Error in Adding Employee", HttpStatus.CONFLICT);
//    }
//
//    @DeleteMapping("/deleteEmp/{empID}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable("empID") int empID){
//        if (employeeService.deleteEmployee(empID)){
//            return new ResponseEntity<String >("Employee Record is Deleted", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<String>("Employee Record cannot be Deleted",HttpStatus.CONFLICT);
//    }
//
//
//
//    @PutMapping("updateEmp")
//    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
//        if (employeeService.updateEmployee(employee)){
//            return new ResponseEntity<>(employee, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>("Record cannot be updated", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
