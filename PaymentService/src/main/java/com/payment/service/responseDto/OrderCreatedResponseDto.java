package com.payment.service.responseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderCreatedResponseDto {

	private Long amount;
	private Integer amount_paid;
	private JSONArray notes ;
	private Date created_at;
	private Long amount_due;
	private String currency;
	private String receipt;
	private String id;
	private String entity;
	private String offer_id;
	private String status;
	private Integer attempts;
	

}
