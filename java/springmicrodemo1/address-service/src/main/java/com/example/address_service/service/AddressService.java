package com.example.address_service.service;

import com.example.address_service.payload.request.AddressRequest;
import com.example.address_service.payload.response.AddressResponse;

public interface AddressService {
	AddressResponse findAddressByEmployeeId(String employeeId);
	AddressResponse createAddress(AddressRequest addressRequest);
	AddressResponse findAddressById(String id);
	
}
