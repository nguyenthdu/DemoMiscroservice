package com.example.address_service.payload.response;

import lombok.Data;

@Data
public class AddressResponse {
	private String id;
	private String city;
	private String state;
}
