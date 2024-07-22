package com.example.employee_service.service;

import com.example.employee_service.payload.request.EmployeeRequest;
import com.example.employee_service.payload.response.EmployeeResponse;

public interface EmployeeService {
	EmployeeResponse getEmployeeById(String id);
	EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
	EmployeeResponse getEmployeeByEmail(String email);
}
