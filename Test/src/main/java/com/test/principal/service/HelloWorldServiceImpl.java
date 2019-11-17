package com.test.principal.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.test.principal.repositories.IHelloWorldService;

@Service
public class HelloWorldServiceImpl implements IHelloWorldService {
  
	@Primary
	public String getHelloWorld() {
		return "Hello world";
	}
	
}
