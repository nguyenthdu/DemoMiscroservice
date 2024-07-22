package com.example.address_service.repository;

import com.example.address_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, String>{
	 @Query(
        nativeQuery = true,
        value
        = "SELECT ea.id, ea.city, ea.state FROM springmicrodemo1.address ea join springmicrodemo1.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
	 Optional<Address> findAddressByEmployeeId(@Param("employeeId") String employeeId);
}
