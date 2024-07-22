package com.example.employee_service.FeignClient;

import com.example.employee_service.payload.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service")
public interface AddressServiceClient {
	@GetMapping("/api/v1/address/{id}")
	AddressResponse getAddressById(@PathVariable("id") String id);
}
