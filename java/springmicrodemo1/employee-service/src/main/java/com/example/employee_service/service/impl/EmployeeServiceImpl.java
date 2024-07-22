package com.example.employee_service.service.impl;

import com.example.employee_service.FeignClient.AddressServiceClient;
import com.example.employee_service.config.KafkaSender;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.payload.request.EmployeeRequest;
import com.example.employee_service.payload.response.AddressResponse;
import com.example.employee_service.payload.response.EmployeeResponse;
import com.example.employee_service.repository.EmployeeRepo;
import com.example.employee_service.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AddressServiceClient addressServiceClient;
	@Autowired
	private KafkaSender kafkaSender;
	
	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
		Employee employee = modelMapper.map(employeeRequest, Employee.class);
		employee = employeeRepo.save(employee);
		// Gửi tin nhắn tới Kafka
		kafkaSender.sendMessage("Employee created with ID: " + employee.getId());
		return mapEmployeeResponse(employee);
	}
	
	@Override
	public EmployeeResponse getEmployeeByEmail(String email) {
		Employee employee = employeeRepo.findByEmail(email);
		return mapEmployeeResponse(employee);
	}
	
	@Override
	public EmployeeResponse getEmployeeById(String id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		return mapEmployeeResponse(employee.orElse(null));
	}
	
	private EmployeeResponse mapEmployeeResponse(Employee employee) {
		if(employee == null) {
			return null;
		}
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		if(employee.getAddressId() != null) {
			AddressResponse addressResponse = addressServiceClient.getAddressById(employee.getAddressId());
			employeeResponse.setAddress(addressResponse);
		}
		return employeeResponse;
	}
}
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//	@Autowired
//	private EmployeeRepo employeeRepo;
//	@Autowired
//	private ModelMapper modelMapper;
//	@Autowired
//	private RestTemplate restTemplate;
//
//	@Override
//	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
//		Employee employee = modelMapper.map(employeeRequest, Employee.class);
//		employee = employeeRepo.save(employee);
//		return mapEmployeeResponse(employee);
//	}
//
//	@Override
//	public EmployeeResponse getEmployeeByEmail(String email) {
//		Employee employee = employeeRepo.findByEmail(email);
//		return mapEmployeeResponse(employee);
//	}
//
//	@Override
//	public EmployeeResponse getEmployeeById(String id) {
//		Optional<Employee> employee = employeeRepo.findById(id);
//		return mapEmployeeResponse(employee.orElse(null));
//	}
//
//	private EmployeeResponse mapEmployeeResponse(Employee employee) {
//		if(employee == null) {
//			return null;
//		}
//		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//		if(employee.getAddressId() != null) {
//			AddressResponse addressResponse = restTemplate.getForObject("http://address-service/api/v1/address/" + employee.getAddressId(), AddressResponse.class);
//			employeeResponse.setAddress(addressResponse);
//		}
//		return employeeResponse;
//	}
//}