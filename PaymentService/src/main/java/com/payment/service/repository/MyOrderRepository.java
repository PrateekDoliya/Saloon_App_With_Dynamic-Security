package com.payment.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.service.entities.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

	public MyOrder findByOrderId( String orderId );
	
}
