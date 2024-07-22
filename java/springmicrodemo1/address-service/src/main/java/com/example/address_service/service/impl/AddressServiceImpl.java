package com.example.address_service.service.impl;

import com.example.address_service.entity.Address;
import com.example.address_service.payload.request.AddressRequest;
import com.example.address_service.payload.response.AddressResponse;
import com.example.address_service.repository.AddressRepo;
import com.example.address_service.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
	private final AddressRepo addressRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressServiceImpl(AddressRepo addressRepo) {
		this.addressRepo = addressRepo;
	}
	
	@Override
	public AddressResponse findAddressByEmployeeId(String employeeId) {
		Optional<Address> addressByEmployeeId = addressRepo.findAddressByEmployeeId(employeeId);
		return modelMapper.map(addressByEmployeeId, AddressResponse.class);
	}
	
	@Override
	public AddressResponse createAddress(AddressRequest addressRequest) {
		Address address = modelMapper.map(addressRequest, Address.class);
		address = addressRepo.save(address);
		return modelMapper.map(address, AddressResponse.class);
	}
	
	@Override
	public AddressResponse findAddressById(String id) {
		Optional<Address> address = addressRepo.findById(id);
		return modelMapper.map(address, AddressResponse.class);
	}
}
