package com.jaymansmann.gameserver.gameserver.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/cors")
@RestController
public class CorsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping()
	public ResponseEntity<?> readMessagesFrom(@RequestParam("url") String url){
		ResponseEntity<String> original = restTemplate.getForEntity(url, String.class);
		MultiValueMap<String, String> newHeaders = new LinkedMultiValueMap<>(original.getHeaders());
		newHeaders.put(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, Arrays.asList("*"));
		newHeaders.put(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, Arrays.asList("*"));
		newHeaders.remove(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN); // supplied by WebConfig
		ResponseEntity<String> response2 = new ResponseEntity<>(original.getBody(), newHeaders, original.getStatusCode());
		return response2;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
}
