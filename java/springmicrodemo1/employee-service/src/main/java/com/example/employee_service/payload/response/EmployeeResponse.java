package com.example.employee_service.payload.response;

import lombok.Data;

@Data
public class EmployeeResponse {
	private String id;
	private String name;
	private String email;
	private String age;
	private String addressId; // Thêm địa chỉ ID
	private AddressResponse address; // Thêm đối tượng AddressResponse
}
