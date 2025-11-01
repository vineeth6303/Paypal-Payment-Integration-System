package com.hulkhiretech.payments.service.impl;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.service.TokenService;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final TokenService tokenService;

	@Override
	public String createOrder() {
		log.info("====>Creating Order in Payment Service Impl");
		String accessToken = tokenService.getAccessToken();
		log.info("====>Access Token received in Payment Service Impl:{}",accessToken);
		/* TODO
		 * 1. Get Access Token
		 * 2. Call paypal createOrder
		 * 3. Success/Failure/Timeout - proper response handling
		 * 4	. What to return to ur calling service(payment-processing-service)
		 */
		return "Order Created from Service - " + accessToken;
	}

	public void method2() {

	}

	@PostConstruct
	public void init()
	{
		log.info("====>PaymentService Impl is initialized");
	}

}
