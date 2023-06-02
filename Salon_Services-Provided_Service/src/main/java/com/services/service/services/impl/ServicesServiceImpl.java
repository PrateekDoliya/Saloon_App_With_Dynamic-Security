package com.services.service.services.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.security.library.payloads.SharedData;
import com.services.service.entities.Employee;
import com.services.service.entities.Services;
import com.services.service.exception.ResourceNotFoundException;
import com.services.service.external.client.service.EmployeeServiceClient;
import com.services.service.repository.ServicesRepository;
import com.services.service.requestDto.ServiceRequestDto;
import com.services.service.responseDto.CommonResponseDto;
import com.services.service.responseDto.ServiceResponseDto;
import com.services.service.services.ServicesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RestTemplate restTemplate;

//	@Autowired
//	private EmployeeServiceClient employeeServiceClient;

	@Override
	public ServiceResponseDto createService(ServiceRequestDto serviceRequestDto) {
		Services services = this.mapper.map(serviceRequestDto, Services.class);
		services.setCustomers(new ArrayList<>());
		Services savedService = this.servicesRepository.save(services);
		savedService.getEmployeeIds().stream().forEach((employeeId) -> {

			String jwtTokenFromRequest = SharedData.getSharedDataMap().get("jwtToken");
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", jwtTokenFromRequest);
			HttpEntity entity = new HttpEntity(headers);

			String url = "lb://EMPLOYEE-SERVICE/api/v1/employee/get/id/" + employeeId;
			ResponseEntity<CommonResponseDto> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, entity,
					CommonResponseDto.class);
			log.info("SERVICES SERVICE IMPL::EMPLOYEE:{}", responseEntity.getBody().getData().toString());
			CommonResponseDto commonResponseDto = responseEntity.getBody();

			Object data = commonResponseDto.getData();
			Employee employee = this.mapper.map(data, Employee.class);
			savedService.getEmployees().add(employee);

		});

		return this.mapper.map(savedService, ServiceResponseDto.class);
	}

	@Override
	public ServiceResponseDto updateService(Integer serviceId, ServiceRequestDto serviceRequestDto) {
		Services serviceById = this.servicesRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "service_id", serviceId));
		serviceById.setService(serviceRequestDto.getService());
//		serviceById.setEmployee(serviceRequestDto.getEmployee());
//		serviceById.setAppointmentBooking(serviceRequestDto.getAppointmentBooking());
		serviceById.setCustomers(serviceRequestDto.getCustomers());
		serviceById.setEmployeeIds(serviceRequestDto.getEmployeeIds());
		Services updateService = this.servicesRepository.save(serviceById);
		return this.mapper.map(updateService, ServiceResponseDto.class);
	}

	@Override
	public ServiceResponseDto deleteService(Integer serviceId) {
		Services serviceById = this.servicesRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "service_id", serviceId));
		this.servicesRepository.delete(serviceById);
		return this.mapper.map(serviceById, ServiceResponseDto.class);
	}

	@Override
	public List<ServiceResponseDto> getAllServices() {
		List<Services> allServices = this.servicesRepository.findAll();
		List<ServiceResponseDto> allServiceResponseDtos = allServices.stream()
				.map(service -> this.mapper.map(service, ServiceResponseDto.class)).collect(Collectors.toList());
		return allServiceResponseDtos;
	}

	@Override
	public ServiceResponseDto getServiceById(Integer serviceId) {
		Services serviceById = this.servicesRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "service_id", serviceId));

		String jwtTokenFromRequest = SharedData.getSharedDataMap().get("jwtToken");

		// USING FEIGN CLIENT
//		CommonResponseDto employeeById = this.employeeServiceClient.getEmployeeById(jwtTokenFromRequest, serviceById.getEmployeeId()).getBody();
//		System.out.println("empById"+ employeeById);

		// USING REST TEMPLATE
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwtTokenFromRequest);
		HttpEntity entity = new HttpEntity(headers);

		serviceById.getEmployeeIds().stream().forEach((employeeId) -> {
			String url = "lb://EMPLOYEE-SERVICE/api/v1/employee/get/id/" + employeeId;
			ResponseEntity<CommonResponseDto> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, entity,
					CommonResponseDto.class);
			log.info("SERVICES SERVICE IMPL::EMPLOYEE:{}", responseEntity.getBody().getData().toString());
			CommonResponseDto commonResponseDto = responseEntity.getBody();

			Object data = commonResponseDto.getData();
			Employee employee = this.mapper.map(data, Employee.class);
			serviceById.getEmployees().add(employee);

		});

//		Object data = commonResponseDto.getData();
//		Employee employeeResponseDto = this.mapper.map(data, Employee.class);

		// ANOTHER WAY OF CONVERTING

//		if(data !=null && data instanceof LinkedHashMap ) {
//			System.out.println("INNSIDE IF");
//			@SuppressWarnings("unchecked")
//			LinkedHashMap<Object, Object> map = (LinkedHashMap<Object, Object>) data;
////			ObjectMapper mapper = new ObjectMapper();
//			ModelMapper mapper2 = new ModelMapper();
//			System.out.println("CON");
//			System.out.println("sajgdhas"+map);
//			employeeResponseDto = mapper2.map(map, EmployeeResponseDto.class);
////			employeeResponseDto = mapper.convertValue(map, EmployeeResponseDto.class);
//			System.out.println("+++++++");
//			System.out.println("+++++++++:"+ employeeResponseDto );
//		}
//		
//		serviceById.setEmployee(employeeResponseDto);;
		return this.mapper.map(serviceById, ServiceResponseDto.class);
	}

	@Override
	public List<ServiceResponseDto> getServiceByName(String serviceName) {
		List<Services> serviceByServiceName = this.servicesRepository.findServiceByServiceName(serviceName);

		String jwtTokenFromRequest = SharedData.getSharedDataMap().get("jwtToken");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwtTokenFromRequest);
		HttpEntity entity = new HttpEntity(headers);

		serviceByServiceName.forEach((service) -> {
			service.getEmployeeIds().stream().forEach((employeeId) -> {

				String url = "lb://EMPLOYEE-SERVICE/api/v1/employee/get/id/" + employeeId;
				ResponseEntity<CommonResponseDto> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET,
						entity, CommonResponseDto.class);
				log.info("SERVICES SERVICE IMPL::EMPLOYEE:{}", responseEntity.getBody().getData().toString());
				CommonResponseDto commonResponseDto = responseEntity.getBody();

				Object data = commonResponseDto.getData();
				Employee employee = this.mapper.map(data, Employee.class);
				service.getEmployees().add(employee);

			});
		});
		List<ServiceResponseDto> serviceByNameResponseDtos = serviceByServiceName.stream()
				.map(service -> this.mapper.map(service, ServiceResponseDto.class)).collect(Collectors.toList());
		return serviceByNameResponseDtos;
	}

	@Override
	public List<ServiceResponseDto> getByEmployeeId(Integer employeeId) {
		List<Services> allServices = this.servicesRepository.findAll();
		List<Services> servicesByEmployee = allServices.stream().filter((service) -> {
			return service.getEmployeeIds().contains(employeeId);
		}).collect(Collectors.toList());
		List<ServiceResponseDto> serviceByEmployeeResponseDtos = servicesByEmployee.stream()
				.map((service) -> this.mapper.map(service, ServiceResponseDto.class)).collect(Collectors.toList());
		return serviceByEmployeeResponseDtos;
	}

}
