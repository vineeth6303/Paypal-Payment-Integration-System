package com.hulkhiretech.payments.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.hulkhiretech.payments.constant.Constant;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.HttpServiceEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

	private final HttpServiceEngine httpServiceEngine;

	//TODO implement Redis cache and take care of expiry of token
	private static String accessToken;
	
	@Value("${paypal.client.id}")
	String clientId;
	
	@Value("${paypal.client.secret}")
	String clientSecret;
	
	@Value("${paypal.oauth.url}")
	String oAuthURL;
	
	
	public String getAccessToken() {
		log.info("====>Retrieving Access Token from Payment Service");
		// TODO implement oAuth token retrieval logic

		if(accessToken != null) {
			log.info("====>Returning cached Access Token");
			return accessToken;
		}

		log.info("====>No cached token found, calling Paypal OAuth service to get new token"); 
		//TODO: Call Paypal OAuth service to get access token
		//Update value of accessToken variable

		log.info("====>Preparing HTTP Headers and Form Data for OAuth Token Request");

		HttpHeaders headers  = new HttpHeaders();

		headers.setBasicAuth(clientId,clientSecret);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);	

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add(Constant.GRANT_TYPE, Constant.CLIENT_CREDENTIALS);

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setHttpMethod(HttpMethod.POST);
		httpRequest.setUri(oAuthURL);
		httpRequest.setHeaders(headers);
		httpRequest.setBody(formData);

		log.info("====>Prepred Request for OAuth call:{}",httpRequest);

		ResponseEntity<String> httpResponse = httpServiceEngine.makeHttpCall(httpRequest);
		log.info("====>HTTP Response received in Token Service:{}",httpResponse);
		
		String tokenBody = httpResponse.getBody();
		log.info("====>Access Token received from OAuth Service and TokenBody is :{}",tokenBody);
		
		return tokenBody;
	}

}
