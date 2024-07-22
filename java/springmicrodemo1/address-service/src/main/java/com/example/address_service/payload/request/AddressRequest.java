package com.example.address_service.payload.request;

import lombok.Data;

@Data
public class AddressRequest {
	private String city;
	private String state;
}
