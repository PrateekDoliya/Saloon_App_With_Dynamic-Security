package com.security.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.library.entitiesDto.AddressDto;

public interface AddressDtorepository extends JpaRepository<AddressDto, Integer> {

}
