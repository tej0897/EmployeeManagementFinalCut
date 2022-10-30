package com.example.EmployeeManagement.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User ID is already exists in DataBase")
public class EmployeeIDAlreadyExists extends Exception{

}
