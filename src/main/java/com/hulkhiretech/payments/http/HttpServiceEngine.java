package com.hulkhiretech.payments.http;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {

	private final RestClient restClient;

	public ResponseEntity<String> makeHttpCall(HttpRequest httpRequest)
	{
		log.info("====>Making HTTP Call in HttpServiceEngine");

		try {
			ResponseEntity<String> HttpResponse = restClient
					.method(httpRequest.getHttpMethod())
					.uri(httpRequest.getUri())
					.headers(restClientHeaders ->restClientHeaders.addAll(httpRequest.getHeaders())) // Using lambda to add headers
					.body(httpRequest.getBody())
					.retrieve()
					.toEntity(String.class);
			log.info("====>HTTP call completed httpResponse: {}", HttpResponse);
			return HttpResponse;
		}
		catch(Exception e)
		{
			log.error("====>Exception while preparing form data : {}", e.getMessage());
			throw new RuntimeException("HTTP call failed in HttpService Engine" + ":" +e.getMessage());
		}

	}
}
