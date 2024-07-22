package com.example.employee_service.controller;

import com.example.employee_service.payload.request.EmployeeRequest;
import com.example.employee_service.payload.response.EmployeeResponse;
import com.example.employee_service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee", description = "Employee API")
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Operation(
			summary = "Create a new employee", description = "Create a new employee with the information provided in the request body", tags = {"Employee", "Create"}
	)
	@ApiResponses(
			{
					@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = EmployeeResponse.class), mediaType = "application/json")}, description = "Employee created successfully"),
					@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
					@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
			}
	)
	@PostMapping
	private ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
		EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable String id) {
		EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	@GetMapping("/email/{email}")
	private ResponseEntity<EmployeeResponse> getEmployeeByEmail(@PathVariable String email) {
		EmployeeResponse employeeResponse = employeeService.getEmployeeByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
}
