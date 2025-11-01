package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.service.interfaces.PaymentService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;
	//TODO:Add create order API
	@PostMapping("/payments")
	public String createOrder()
	{
		log.info("====>Create Ordering in Paypal provider service");
		String response =  paymentService.createOrder();
		log.info("====>Response from Payment Service:{}",response);
		return response;
	}

	@PostConstruct
	public void init()
	{
		log.info("===>Payment Controller initiated Payment Service:{}",paymentService);
	}


}
