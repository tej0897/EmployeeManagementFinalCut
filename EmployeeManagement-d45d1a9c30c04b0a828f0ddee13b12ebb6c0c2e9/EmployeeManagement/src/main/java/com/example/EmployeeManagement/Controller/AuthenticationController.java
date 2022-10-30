package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.Exceptions.EmployeeIDAlreadyExists;
import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;

@RestController
@RequestMapping("auth/emp")
public class AuthenticationController {

    private Map<String, String> mapObj = new HashMap<String, String>();
    private EmployeeService employeeService;

    @Autowired
    public AuthenticationController(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    @PostMapping("/registerEmp")
    public ResponseEntity<?> addEmp(@RequestBody Employee employee) throws EmployeeIDAlreadyExists {
        if (employeeService.addEmployee(employee)!=null){
            return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Employee not created", HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<?> empLogin(@RequestBody Employee employee){
        try{

            String jwtToken = generateToken(employee.getEmpName(), employee.getPassword());
            mapObj.put("message", "Log In Success");
            mapObj.put("token", jwtToken);

        } catch (Exception e){
            mapObj.put("message", "Log In Failure");
            mapObj.put("token", null);
            return new ResponseEntity<>("User Credentials are not Valid", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(mapObj, HttpStatus.ACCEPTED);
    }


    public String generateToken(String empName, String password) throws ServletException, Exception {
        String jwtToken = "";
        if (empName == null || password == null){
            throw new ServletException("Please enter valid Emp Name and Password");
        }

        boolean flag = employeeService.validateEmployee(empName, password);

        if (!flag){
            throw new ServletException("Invalid Emp name and password");
        } else {
            jwtToken = Jwts.builder()
                    .setSubject(empName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                    .signWith(SignatureAlgorithm.HS256, "secret key")
                    .compact();
        }
        return jwtToken;
    }
}
