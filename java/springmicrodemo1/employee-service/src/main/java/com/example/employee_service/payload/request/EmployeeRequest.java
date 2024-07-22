package com.example.employee_service.payload.request;

import lombok.Data;

@Data
public class EmployeeRequest {
	private String name;
	private String email;
	private String age;
	private String addressId; // Thêm địa chỉ ID
}
