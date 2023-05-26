package com.sloon.customer.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cumtomer_name")
	private String customerName;
	
	@Column(name="mobile_nubmer")
	private String mobileNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "date_of_appointment")
	private LocalDate dateOfAppointment;
	
	@Column(name = "date_of_booking")
	private LocalDate dateOfBooking;
	
	@Column(name = "employee_id")
	private Integer employee;

}
