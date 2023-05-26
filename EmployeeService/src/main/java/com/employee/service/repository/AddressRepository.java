package com.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.service.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
