package com.payment.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long myOrderId;
	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	private String paymentId;
	private String userEmail;
	
	

	
}
