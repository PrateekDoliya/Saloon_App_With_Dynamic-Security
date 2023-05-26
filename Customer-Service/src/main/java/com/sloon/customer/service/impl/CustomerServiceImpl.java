package com.sloon.customer.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sloon.customer.dao.CustomerRepository;
import com.sloon.customer.entity.Customer;
import com.sloon.customer.payloads.CommonResponse;
import com.sloon.customer.payloads.CustomerDTO;
import com.sloon.customer.payloads.EmployeeDTO;
import com.sloon.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public CommonResponse addCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customer.setDateOfBooking(LocalDate.now());
		customer = customerRepository.save(customer);
		return CommonResponse.builder().data(modelMapper.map(customer, CustomerDTO.class)).timeStamp(LocalDate.now())
				.status(true).build();
	}

	@Override
	public CommonResponse getAllCustomes() {
		List<Customer> all = customerRepository.findAll();
		List<CustomerDTO> allCustomerDto = all.stream().map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
		
		allCustomerDto.stream().forEach( (customer) -> {
			HttpEntity entity = new HttpEntity(null);
			
			ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
					"lb://EMPLOYEE-SERVICE/api/v1/employee/get/id/" + customer.getEmployee(), HttpMethod.GET, entity,
					CommonResponse.class);
			CommonResponse commonResponse = responseEntity.getBody();
			EmployeeDTO employeeDTO = this.modelMapper.map(commonResponse.getData(), EmployeeDTO.class);

			customer.setEmployeeDTO(employeeDTO);

		});
		
		return CommonResponse.builder().data(allCustomerDto).timeStamp(LocalDate.now()).status(true).build();
	}

	@Override
	public CommonResponse getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not foud with id" + id));
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		
		HttpEntity entity = new HttpEntity(null);
		
		ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
				"lb://EMPLOYEE-SERVICE/api/v1/employee/get/id/" + id, HttpMethod.GET, entity,
				CommonResponse.class);
		CommonResponse commonResponse = responseEntity.getBody();

		EmployeeDTO employeeDTO = this.modelMapper.map(commonResponse.getData(), EmployeeDTO.class);
		customerDTO.setEmployeeDTO(employeeDTO);;
		return CommonResponse.builder().data(customerDTO).timeStamp(LocalDate.now()).status(true).build();

	}

}
