package com.sloon.customer.service;

import com.sloon.customer.payloads.CommonResponse;
import com.sloon.customer.payloads.CustomerDTO;

public interface CustomerService {

	CommonResponse addCustomer(CustomerDTO customerDTO);
	
	CommonResponse getAllCustomes();
	
	CommonResponse getCustomerById(Integer id);
}
