package com.example.address_service.controller;

import com.example.address_service.payload.request.AddressRequest;
import com.example.address_service.payload.response.AddressResponse;
import com.example.address_service.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<AddressResponse> findAddressByEmployeeId(@PathVariable String employeeId) {
		AddressResponse addressResponse = addressService.findAddressByEmployeeId(employeeId);
		return ResponseEntity.ok(addressResponse);
	}
	@PostMapping
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {
		AddressResponse addressResponse = addressService.createAddress(addressRequest);
		return ResponseEntity.ok(addressResponse);
	}
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> findAddressById(@PathVariable String id) {
		AddressResponse addressResponse = addressService.findAddressById(id);
		return ResponseEntity.ok(addressResponse);
	}
}
